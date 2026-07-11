package dataaccess;

import domain.BitmapItem;
import domain.Presentation;
import domain.Slide;
import domain.SlideItem;
import domain.TextItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XMLAccessorTest
{
	private static final String DTD_CONTENT = """
		<!--
		 Document Type Definition voor jabberpoint.JabberPoint presentatties
		 $Id$
		 -->
		        <!ELEMENT showtitle (#PCDATA)>
		        <!ELEMENT presentation (showtitle,slide+)>
		        <!ELEMENT title (#PCDATA)>
		        <!ELEMENT slide (title, item*)>
		        <!ELEMENT item (#PCDATA)>
		        <!ATTLIST item kind CDATA #REQUIRED>
		        <!ATTLIST item level CDATA #REQUIRED>
		""";

	private static final String VALID_XML =
		"<?xml version=\"1.0\"?>\n" +
			"<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">\n" +
			"<presentation>\n" +
			"<showtitle>Test Presentation</showtitle>\n" +
			"<slide>\n" +
			"<title>Slide One</title>\n" +
			"<item kind=\"text\" level=\"1\">Hello</item>\n" +
			"<item kind=\"image\" level=\"2\">picture.jpg</item>\n" +
			"</slide>\n" +
			"<slide>\n" +
			"<title>Slide Two</title>\n" +
			"<item kind=\"text\" level=\"1\">World</item>\n" +
			"</slide>\n" +
			"</presentation>\n";

	private static final String XML_WITH_UNKNOWN_KIND =
		"<?xml version=\"1.0\"?>\n" +
			"<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">\n" +
			"<presentation>\n" +
			"<showtitle>Test Presentation</showtitle>\n" +
			"<slide>\n" +
			"<title>Slide One</title>\n" +
			"<item kind=\"video\" level=\"1\">clip.mp4</item>\n" +
			"<item kind=\"text\" level=\"1\">Hello</item>\n" +
			"</slide>\n" +
			"</presentation>\n";

	private void writeDtd(Path directory) throws IOException
	{
		Files.writeString(directory.resolve("jabberpoint.dtd"), DTD_CONTENT);
	}

	@Test
	public void loadFile_validXml_populatesPresentationWithSlidesAndItems(@TempDir Path tempDir) throws IOException
	{
		writeDtd(tempDir);
		Path xmlFile = tempDir.resolve("test.xml");
		Files.writeString(xmlFile, VALID_XML);

		Presentation presentation = new Presentation();
		new XMLAccessor().loadFile(presentation, xmlFile.toString());

		assertEquals("Test Presentation", presentation.getTitle());
		assertEquals(2, presentation.getSize());

		Slide slideOne = presentation.getSlide(0);
		assertEquals("Slide One", slideOne.getTitle());
		assertEquals(2, slideOne.getSize());

		SlideItem firstItem = slideOne.getSlideItem(0);
		assertTrue(firstItem instanceof TextItem);
		assertEquals("Hello", ((TextItem) firstItem).getText());
		assertEquals(1, firstItem.getLevel());

		SlideItem secondItem = slideOne.getSlideItem(1);
		assertTrue(secondItem instanceof BitmapItem);
		assertEquals("picture.jpg", ((BitmapItem) secondItem).getName());
		assertEquals(2, secondItem.getLevel());

		Slide slideTwo = presentation.getSlide(1);
		assertEquals("Slide Two", slideTwo.getTitle());
		assertEquals(1, slideTwo.getSize());
	}

	@Test
	public void loadFile_itemWithUnknownKind_isSkippedWithoutThrowing(@TempDir Path tempDir) throws IOException
	{
		writeDtd(tempDir);
		Path xmlFile = tempDir.resolve("test.xml");
		Files.writeString(xmlFile, XML_WITH_UNKNOWN_KIND);

		Presentation presentation = new Presentation();
		new XMLAccessor().loadFile(presentation, xmlFile.toString());

		Slide slide = presentation.getSlide(0);
		assertEquals(1, slide.getSize());
		assertTrue(slide.getSlideItem(0) instanceof TextItem);
	}

	@Test
	public void saveFile_thenLoadFile_roundTripsPresentationData(@TempDir Path tempDir) throws IOException
	{
		writeDtd(tempDir);

		Presentation original = new Presentation();
		original.setTitle("Round Trip");
		Slide slide = new Slide();
		slide.setTitle("Slide A");
		slide.append(new TextItem(1, "Hi"));
		slide.append(new BitmapItem(2, "picture.jpg"));
		original.append(slide);

		Path xmlFile = tempDir.resolve("roundtrip.xml");
		XMLAccessor accessor = new XMLAccessor();
		accessor.saveFile(original, xmlFile.toString());

		Presentation reloaded = new Presentation();
		accessor.loadFile(reloaded, xmlFile.toString());

		assertEquals("Round Trip", reloaded.getTitle());
		assertEquals(1, reloaded.getSize());

		Slide reloadedSlide = reloaded.getSlide(0);
		assertEquals("Slide A", reloadedSlide.getTitle());
		assertEquals(2, reloadedSlide.getSize());

		SlideItem textItem = reloadedSlide.getSlideItem(0);
		assertTrue(textItem instanceof TextItem);
		assertEquals("Hi", ((TextItem) textItem).getText());

		SlideItem bitmapItem = reloadedSlide.getSlideItem(1);
		assertTrue(bitmapItem instanceof BitmapItem);
		assertEquals("picture.jpg", ((BitmapItem) bitmapItem).getName());
	}
}
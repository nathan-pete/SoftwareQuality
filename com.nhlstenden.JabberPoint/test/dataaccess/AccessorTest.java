package dataaccess;

import domain.PresentationObserver;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccessorTest
{
	@Test
	public void getFileLoader_xmlExtension_returnsXMLAccessor() throws IOException
	{
		PresentationLoader loader = Accessor.getFileLoader("test.xml");
		assertInstanceOf(XMLAccessor.class, loader);
	}

	@Test
	public void getFileLoader_uppercaseExtension_returnsXMLAccessor() throws IOException
	{
		PresentationLoader loader = Accessor.getFileLoader("TEST.XML");
		assertInstanceOf(XMLAccessor.class, loader);
	}

	@Test
	public void getFileSaver_xmlExtension_returnsXMLAccessor() throws IOException
	{
		PresentationSaver saver = Accessor.getFileSaver("dump.xml");
		assertInstanceOf(XMLAccessor.class, saver);
	}

	@Test
	public void getFileLoader_unrecognizedExtension_throwsIOException()
	{
		assertThrows(IOException.class, () -> Accessor.getFileLoader("data.yaml"));
	}

	@Test
	public void getFileLoader_noExtension_throwsIOException()
	{
		assertThrows(IOException.class, () -> Accessor.getFileLoader("noextension"));
	}

	@Test
	public void getFileLoader_filenameEndingInDot_throwsIOException()
	{
		assertThrows(IOException.class, () -> Accessor.getFileLoader("trailingdot."));
	}

	@Test
	public void getDemoAccessor_always_returnsDemoPresentation()
	{
		PresentationLoader demoAccessor = Accessor.getDemoAccessor();
		assertInstanceOf(DemoPresentation.class, demoAccessor);
	}
}
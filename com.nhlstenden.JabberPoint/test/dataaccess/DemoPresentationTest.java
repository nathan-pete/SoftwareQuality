package dataaccess;

import domain.BitmapItem;
import domain.Presentation;
import domain.Slide;
import domain.SlideItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoPresentationTest
{
	private final DemoPresentation demoPresentation = new DemoPresentation();

	@Test
	public void loadFile_always_setsExpectedTitle()
	{
		Presentation presentation = new Presentation();
		demoPresentation.loadFile(presentation, "");
		assertEquals("Demo Presentation", presentation.getTitle());
	}

	@Test
	public void loadFile_always_createsThreeSlides()
	{
		Presentation presentation = new Presentation();
		demoPresentation.loadFile(presentation, "");
		assertEquals(3, presentation.getSize());
	}

	@Test
	public void loadFile_always_firstSlideHasExpectedTitleAndItemCount()
	{
		Presentation presentation = new Presentation();
		demoPresentation.loadFile(presentation, "");
		Slide firstSlide = presentation.getSlide(0);
		assertEquals("JabberPoint", firstSlide.getTitle());
		assertEquals(10, firstSlide.getSize());
	}

	@Test
	public void loadFile_always_thirdSlideEndsWithBitmapItem()
	{
		Presentation presentation = new Presentation();
		demoPresentation.loadFile(presentation, "");
		Slide thirdSlide = presentation.getSlide(2);
		SlideItem lastItem = thirdSlide.getSlideItem(thirdSlide.getSize() - 1);
		assertTrue(lastItem instanceof BitmapItem);
		assertEquals("JabberPoint.jpg", ((BitmapItem) lastItem).getName());
	}

	@Test
	public void loadFile_unusedFilenameArgument_isIgnored()
	{
		Presentation presentation = new Presentation();
		demoPresentation.loadFile(presentation, "this-value-should-not-matter.xml");
		assertEquals(3, presentation.getSize());
	}
}
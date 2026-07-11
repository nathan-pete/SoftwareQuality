package dataaccess;

import domain.BitmapItem;
import domain.SlideItem;
import domain.TextItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SlideItemFactoryTest
{
	private final SlideItemFactory factory = new SlideItemFactory();

	@Test
	public void createSlideItem_textType_returnsTextItemWithGivenLevelAndContent()
	{
		SlideItem result = factory.createSlideItem(2, "text", "Hello");
		assertTrue(result instanceof TextItem);
		assertEquals(2, result.getLevel());
		assertEquals("Hello", ((TextItem) result).getText());
	}

	@Test
	public void createSlideItem_imageType_returnsBitmapItemWithGivenLevelAndName()
	{
		SlideItem result = factory.createSlideItem(1, "image", "JabberPoint.jpg");
		assertTrue(result instanceof BitmapItem);
		assertEquals(1, result.getLevel());
		assertEquals("JabberPoint.jpg", ((BitmapItem) result).getName());
	}

	@Test
	public void createSlideItem_unknownType_returnsNull()
	{
		SlideItem result = factory.createSlideItem(1, "video", "clip.mp4");
		assertNull(result);
	}
}
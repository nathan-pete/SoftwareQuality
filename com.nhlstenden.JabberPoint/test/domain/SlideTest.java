package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SlideTest
{
	private static Graphics2D testGraphics;

	@BeforeAll
	public static void setUp()
	{
		Style.createStyles();
		BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		testGraphics = buffer.createGraphics();
	}

	@Test
	public void getSize_newSlide_returnsZero()
	{
		Slide slide = new Slide();
		assertEquals(0, slide.getSize());
	}

	@Test
	public void append_slideItem_increasesSize()
	{
		Slide slide = new Slide();
		slide.append(new TextItem(1, "Hello"));
		assertEquals(1, slide.getSize());
	}

	@Test
	public void getSlideItem_afterAppend_returnsSameItem()
	{
		Slide slide = new Slide();
		TextItem item = new TextItem(1, "Hello");
		slide.append(item);
		assertEquals(item, slide.getSlideItem(0));
	}

	@Test
	public void append_levelAndMessage_createsTextItemWithThatLevelAndText()
	{
		Slide slide = new Slide();
		slide.append(2, "Hello");
		SlideItem result = slide.getSlideItem(0);
		assertTrue(result instanceof TextItem);
		assertEquals(2, result.getLevel());
		assertEquals("Hello", ((TextItem) result).getText());
	}

	@Test
	public void getSlideItems_afterAppends_returnsVectorWithCorrectSize()
	{
		Slide slide = new Slide();
		slide.append(1, "First");
		slide.append(2, "Second");
		Vector<SlideItem> items = slide.getSlideItems();
		assertEquals(2, items.size());
	}

	@Test
	public void getTitle_afterSetTitle_returnsSameTitle()
	{
		Slide slide = new Slide();
		slide.setTitle("My Slide");
		assertEquals("My Slide", slide.getTitle());
	}

	@Test
	public void draw_slideWithTitleAndItems_doesNotThrow()
	{
		Slide slide = new Slide();
		slide.setTitle("My Slide");
		slide.append(1, "First item");
		slide.append(2, "Second item");
		Rectangle area = new Rectangle(0, 0, Slide.WIDTH, Slide.HEIGHT);
		assertDoesNotThrow(() -> slide.draw(testGraphics, area, null));
	}

	@Test
	public void draw_emptySlideWithNullTitle_doesNotThrow()
	{
		Slide slide = new Slide();
		Rectangle area = new Rectangle(0, 0, Slide.WIDTH, Slide.HEIGHT);
		assertDoesNotThrow(() -> slide.draw(testGraphics, area, null));
	}
}
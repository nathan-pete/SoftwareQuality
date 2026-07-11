package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BitmapItemTest
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
	public void constructor_missingImage_doesNotThrow()
	{
		assertDoesNotThrow(() -> new BitmapItem(1, "does-not-exist.jpg"));
	}

	@Test
	public void getName_missingImage_returnsGivenFilename()
	{
		BitmapItem item = new BitmapItem(1, "does-not-exist.jpg");
		assertEquals("does-not-exist.jpg", item.getName());
	}

	@Test
	public void constructor_noArgs_producesNullImageName()
	{
		BitmapItem item = new BitmapItem();
		assertNull(item.getName());
	}

	@Test
	public void getBoundingBox_missingImage_returnsZeroWidthPlaceholder()
	{
		BitmapItem item = new BitmapItem(1, "does-not-exist.jpg");
		Style style = Style.getStyle(1);
		Rectangle box = item.getBoundingBox(testGraphics, null, 1.0f, style);
		assertEquals(0, box.width);
		assertEquals((int) (style.leading * 1.0f), box.height);
	}

	@Test
	public void draw_missingImage_doesNotThrow()
	{
		BitmapItem item = new BitmapItem(1, "does-not-exist.jpg");
		Style style = Style.getStyle(1);
		assertDoesNotThrow(() -> item.draw(0, 0, 1.0f, testGraphics, style, null));
	}

	@Test
	public void getBoundingBox_loadedImage_usesImageDimensions()
	{
		BitmapItem item = new BitmapItem(1, "JabberPoint.jpg");
		Style style = Style.getStyle(1);
		Rectangle box = item.getBoundingBox(testGraphics, null, 1.0f, style);
		assertTrue(box.width > 0, "Expected a real image width, was the resource found on the classpath?");
	}

	@Test
	public void draw_loadedImage_doesNotThrow()
	{
		BitmapItem item = new BitmapItem(1, "JabberPoint.jpg");
		Style style = Style.getStyle(1);
		assertDoesNotThrow(() -> item.draw(0, 0, 1.0f, testGraphics, style, null));
	}

	@Test
	public void toString_always_includesLevelAndImageName()
	{
		BitmapItem item = new BitmapItem(2, "JabberPoint.jpg");
		assertEquals("BitmapItem[2,JabberPoint.jpg]", item.toString());
	}
}
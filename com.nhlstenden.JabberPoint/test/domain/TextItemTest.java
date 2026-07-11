package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextItemTest
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
	public void getText_nullText_returnsEmptyString()
	{
		TextItem item = new TextItem(1, null);
		assertEquals("", item.getText());
	}

	@Test
	public void getText_givenString_returnsSameString()
	{
		TextItem item = new TextItem(1, "Hello");
		assertEquals("Hello", item.getText());
	}

	@Test
	public void constructor_noArgs_usesDefaultText()
	{
		TextItem item = new TextItem();
		assertEquals("No Text Given", item.getText());
	}

	@Test
	public void getAttributedString_emptyText_doesNotThrow()
	{
		TextItem item = new TextItem(1, "");
		Style style = Style.getStyle(1);
		AttributedString result = assertDoesNotThrow(() -> item.getAttributedString(style, 1.0f));
		assertNotNull(result);
	}

	@Test
	public void getBoundingBox_normalText_returnsNonNegativeSize()
	{
		TextItem item = new TextItem(1, "Hello world");
		Style style = Style.getStyle(1);
		Rectangle box = item.getBoundingBox(testGraphics, null, 1.0f, style);
		assertTrue(box.width >= 0);
		assertTrue(box.height >= 0);
	}

	@Test
	public void draw_nullText_doesNotThrow()
	{
		TextItem item = new TextItem(1, null);
		Style style = Style.getStyle(1);
		assertDoesNotThrow(() -> item.draw(0, 0, 1.0f, testGraphics, style, null));
	}

	@Test
	public void draw_normalText_doesNotThrow()
	{
		TextItem item = new TextItem(1, "Hello world");
		Style style = Style.getStyle(1);
		assertDoesNotThrow(() -> item.draw(0, 0, 1.0f, testGraphics, style, null));
	}

	@Test
	public void toString_always_includesLevelAndText()
	{
		TextItem item = new TextItem(2, "Hello");
		assertEquals("TextItem[2,Hello]", item.toString());
	}
}
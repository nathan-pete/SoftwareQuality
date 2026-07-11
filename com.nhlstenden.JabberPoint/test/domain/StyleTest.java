package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Font;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StyleTest
{
	@BeforeEach
	public void setUp()
	{
		Style.createStyles();
	}

	@Test
	public void createStyles_afterCall_allFiveLevelsNotNull()
	{
		for (int level = 0; level < 5; level++)
		{
			assertNotNull(Style.getStyle(level), "Style for level " + level + " should not be null");
		}
	}

	@Test
	public void getStyle_levelAboveRange_returnsLastStyle()
	{
		Style lastStyle = Style.getStyle(4);
		Style outOfRangeStyle = Style.getStyle(10);
		assertEquals(lastStyle, outOfRangeStyle);
	}

	@Test
	public void constructor_givenValues_setsAllFields()
	{
		Style style = new Style(20, Color.blue, 40, 10);
		assertEquals(20, style.indent);
		assertEquals(Color.blue, style.color);
		assertEquals(40, style.fontSize);
		assertEquals(10, style.leading);
	}

	@Test
	public void constructor_givenPointSize_buildsBoldFontAtThatSize()
	{
		Style style = new Style(20, Color.blue, 40, 10);
		assertEquals(Font.BOLD, style.font.getStyle());
		assertEquals(40, style.font.getSize());
	}

	@Test
	public void getFont_givenScale_returnsScaledFontSize()
	{
		Style style = new Style(20, Color.blue, 40, 10);
		Font scaledFont = style.getFont(2.0f);
		assertEquals(80.0f, scaledFont.getSize2D());
	}

	@Test
	public void toString_always_includesIndentColorFontSizeAndLeading()
	{
		Style style = new Style(20, Color.blue, 40, 10);
		String result = style.toString();
		assertEquals("[20," + Color.blue + "; 40 on 10]", result);
	}
}
package dataaccess;

import domain.BitmapItem;
import domain.SlideItem;
import domain.TextItem;

public class SlideItemFactory
{

	protected static final String TEXT = "text";
	protected static final String IMAGE = "image";
	protected static final String UNKNOWN_TYPE = "Unknown Element type";

	public SlideItem createSlideItem(int level, String type, String content)
	{
		if (TEXT.equals(type))
		{
			return new TextItem(level, content);
		}
		else if (IMAGE.equals(type))
		{
			return new BitmapItem(level, content);
		}
		else
		{
			System.err.println(UNKNOWN_TYPE);
			return null;
		}
	}
}
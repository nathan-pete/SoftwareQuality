package dataaccess;

import domain.BitmapItem;
import domain.SlideItem;

import java.io.PrintWriter;

public class BitmapItemXMLWriter implements SlideItemWriter
{
	protected static final String KIND = "image";
	
	public void write(SlideItem item, PrintWriter out)
	{
		BitmapItem bitmapItem = (BitmapItem) item;
		out.print("<item kind=\"" + KIND + "\" level=\"" + bitmapItem.getLevel() + "\">");
		out.print(bitmapItem.getName());
		out.println("</item>");
	}
}

package dataaccess;

import domain.SlideItem;
import domain.TextItem;

import java.io.PrintWriter;

public class TextItemXMLWriter implements SlideItemWriter
{
	protected static final String KIND = "text";
	
	public void write(SlideItem item, PrintWriter out)
	{
		TextItem textItem = (TextItem) item;
		out.print("<item kind=\"" + KIND + "\" level=\"" + textItem.getLevel() + "\">");
		out.print(textItem.getText());
		out.println("</item>");
	}
}

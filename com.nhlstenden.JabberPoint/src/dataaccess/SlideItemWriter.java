package dataaccess;

import domain.SlideItem;

import java.io.PrintWriter;

public interface SlideItemWriter
{
	public void write(SlideItem item, PrintWriter out);
}

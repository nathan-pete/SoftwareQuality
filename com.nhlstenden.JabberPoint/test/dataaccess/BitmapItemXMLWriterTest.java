package dataaccess;

import domain.BitmapItem;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitmapItemXMLWriterTest
{
	private final BitmapItemXMLWriter writer = new BitmapItemXMLWriter();

	@Test
	public void write_bitmapItem_producesCorrectXmlElement()
	{
		StringWriter stringWriter = new StringWriter();
		PrintWriter out = new PrintWriter(stringWriter);
		BitmapItem item = new BitmapItem(1, "picture.jpg");

		writer.write(item, out);
		out.flush();

		String expected = "<item kind=\"image\" level=\"1\">picture.jpg</item>" + System.lineSeparator();
		assertEquals(expected, stringWriter.toString());
	}
}
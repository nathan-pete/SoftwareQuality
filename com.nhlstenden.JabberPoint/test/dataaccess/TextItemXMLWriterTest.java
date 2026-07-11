package dataaccess;

import domain.TextItem;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextItemXMLWriterTest
{
	private final TextItemXMLWriter writer = new TextItemXMLWriter();

	@Test
	public void write_textItem_producesCorrectXmlElement()
	{
		StringWriter stringWriter = new StringWriter();
		PrintWriter out = new PrintWriter(stringWriter);
		TextItem item = new TextItem(2, "Hello world");

		writer.write(item, out);
		out.flush();

		String expected = "<item kind=\"text\" level=\"2\">Hello world</item>" + System.lineSeparator();
		assertEquals(expected, stringWriter.toString());
	}

	@Test
	public void write_textItemWithEmptyText_producesEmptyContentElement()
	{
		StringWriter stringWriter = new StringWriter();
		PrintWriter out = new PrintWriter(stringWriter);
		TextItem item = new TextItem(1, "");

		writer.write(item, out);
		out.flush();

		String expected = "<item kind=\"text\" level=\"1\"></item>" + System.lineSeparator();
		assertEquals(expected, stringWriter.toString());
	}
}
package ui;

import dataaccess.PresentationLoader;
import domain.Presentation;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

public class OpenCommand implements Command
{

	protected static final String TESTFILE = "test.xml";
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";

	private Presentation presentation;
	private PresentationLoader loader;
	private Frame parent;

	public OpenCommand(Presentation presentation, PresentationLoader loader, Frame parent)
	{
		this.presentation = presentation;
		this.loader = loader;
		this.parent = parent;
	}

	public void execute()
	{
		presentation.clear();
		try
		{
			loader.loadFile(presentation, TESTFILE);
			presentation.setSlideNumber(0);
		} catch (IOException exc)
		{
			JOptionPane.showMessageDialog(parent, IOEX + exc,
				LOADERR, JOptionPane.ERROR_MESSAGE);
		}
		parent.repaint();
	}
}
package ui;

import dataaccess.Accessor;
import dataaccess.PresentationLoader;
import domain.Presentation;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

public class OpenCommand implements Command
{

	protected static final String TESTFILE = "SoftwareQuality/com.nhlstenden.JabberPoint/src/resources/test.xml";
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";

	private Presentation presentation;
	private Frame parent;

	public OpenCommand(Presentation presentation, Frame parent)
	{
		this.presentation = presentation;
		this.parent = parent;
	}

	public void execute()
	{
		presentation.clear();
		try
		{
			// Dynamically resolved based on TESTFILE's extension, only at
			// the moment a file is actually being opened.
			PresentationLoader loader = Accessor.getFileLoader(TESTFILE);
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
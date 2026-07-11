package ui;

import dataaccess.Accessor;
import dataaccess.PresentationSaver;
import domain.Presentation;

import java.awt.Frame;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SaveCommand implements Command
{

	protected static final String SAVEFILE = "dump.xml";
	protected static final String IOEX = "IO Exception: ";
	protected static final String SAVEERR = "Save Error";

	private Presentation presentation;
	private Frame parent;

	public SaveCommand(Presentation presentation, Frame parent)
	{
		this.presentation = presentation;
		this.parent = parent;
	}

	public void execute()
	{
		try
		{
			// Dynamically resolved based on SAVEFILE's extension, only at
			// the moment a file is actually being saved.
			PresentationSaver saver = Accessor.getFileSaver(SAVEFILE);
			saver.saveFile(presentation, SAVEFILE);
		} catch (IOException exc)
		{
			JOptionPane.showMessageDialog(parent, IOEX + exc,
				SAVEERR, JOptionPane.ERROR_MESSAGE);
		}
	}
}
import java.awt.Frame;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SaveCommand implements Command
{

	protected static final String SAVEFILE = "dump.xml";
	protected static final String IOEX = "IO Exception: ";
	protected static final String SAVEERR = "Save Error";

	private Presentation presentation;
	private PresentationSaver saver;
	private Frame parent;

	public SaveCommand(Presentation presentation, PresentationSaver saver, Frame parent)
	{
		this.presentation = presentation;
		this.saver = saver;
		this.parent = parent;
	}

	public void execute()
	{
		try
		{
			saver.saveFile(presentation, SAVEFILE);
		} catch (IOException exc)
		{
			JOptionPane.showMessageDialog(parent, IOEX + exc,
				SAVEERR, JOptionPane.ERROR_MESSAGE);
		}
	}
}
package ui;

import domain.Presentation;

import javax.swing.JOptionPane;

public class GotoSlideCommand implements Command
{

	protected static final String PAGENR = "Page number?";

	private Presentation presentation;

	public GotoSlideCommand(Presentation presentation)
	{
		this.presentation = presentation;
	}

	public void execute()
	{
		String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
		int pageNumber = Integer.parseInt(pageNumberStr);
		presentation.setSlideNumber(pageNumber - 1);
	}
}
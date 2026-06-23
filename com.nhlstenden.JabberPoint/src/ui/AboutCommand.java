package ui;

import java.awt.Frame;

// ui.AboutCommand shows the application's About dialog.
public class AboutCommand implements Command
{

	private Frame parent;

	public AboutCommand(Frame parent)
	{
		this.parent = parent;
	}

	public void execute()
	{
		AboutBox.show(parent);
	}
}


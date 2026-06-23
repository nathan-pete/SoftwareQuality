import java.awt.*;

public class NewCommand implements Command
{

	private Presentation presentation;
	private Frame parent;

	public NewCommand(Presentation presentation, Frame parent)
	{
		this.presentation = presentation;
		this.parent = parent;
	}

	public void execute()
	{
		presentation.clear();
		parent.repaint();
	}
}
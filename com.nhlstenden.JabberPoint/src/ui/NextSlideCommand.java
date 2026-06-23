package ui;

import domain.Presentation;

public class NextSlideCommand implements Command
{

	private Presentation presentation;

	public NextSlideCommand(Presentation presentation)
	{
		this.presentation = presentation;
	}

	public void execute()
	{
		presentation.nextSlide();
	}
}
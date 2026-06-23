package ui;

import domain.Presentation;

public class PrevSlideCommand implements Command
{
	private Presentation presentation;

	public PrevSlideCommand(Presentation presentation)
	{
		this.presentation = presentation;
	}

	public void execute()
	{
		presentation.prevSlide();
	}
}
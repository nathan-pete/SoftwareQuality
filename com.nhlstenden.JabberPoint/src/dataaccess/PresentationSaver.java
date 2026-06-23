package dataaccess;

import domain.Presentation;

import java.io.IOException;

public interface PresentationSaver
{
	public void saveFile(Presentation p, String fn) throws IOException;
}

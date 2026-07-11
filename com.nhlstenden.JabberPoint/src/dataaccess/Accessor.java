package dataaccess;

import domain.Presentation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * <p>An Accessor makes it possible to read or write data for a presentation.</p>
 * <p>Non-abstract subclasses must implement the load and save methods.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public abstract class Accessor implements PresentationLoader, PresentationSaver
{
	public static final String DEMO_NAME = "Demonstration presentation";
	public static final String DEFAULT_EXTENSION = ".xml";

	protected static final String UNSUPPORTED_EXTENSION = "No accessor registered for file extension: ";
	
	private static final Map<String, Supplier<Accessor>> ACCESSORS = createAccessorRegistry();

	private static Map<String, Supplier<Accessor>> createAccessorRegistry()
	{
		Map<String, Supplier<Accessor>> registry = new HashMap<String, Supplier<Accessor>>();
		registry.put("xml", XMLAccessor::new);
		return registry;
	}

	public static PresentationLoader getDemoAccessor()
	{
		return new DemoPresentation();
	}
	
	public static PresentationLoader getFileLoader(String filename) throws IOException
	{
		return resolveAccessor(filename);
	}

	public static PresentationSaver getFileSaver(String filename) throws IOException
	{
		return resolveAccessor(filename);
	}

	private static Accessor resolveAccessor(String filename) throws IOException
	{
		String extension = extractExtension(filename);
		Supplier<Accessor> supplier = ACCESSORS.get(extension);
		if (supplier == null)
		{
			throw new IOException(UNSUPPORTED_EXTENSION + extension);
		}
		return supplier.get();
	}

	private static String extractExtension(String filename)
	{
		int dotIndex = filename.lastIndexOf('.');
		if (dotIndex < 0 || dotIndex == filename.length() - 1)
		{
			return "";
		}
		return filename.substring(dotIndex + 1).toLowerCase();
	}

	public Accessor()
	{
	}

	abstract public void loadFile(Presentation p, String fn) throws IOException;

	abstract public void saveFile(Presentation p, String fn) throws IOException;

}
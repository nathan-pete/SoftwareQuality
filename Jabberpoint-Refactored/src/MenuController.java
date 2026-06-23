import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{

	private Frame parent; // the frame, only used as parent for the Dialogs
	private Presentation presentation; // Commands are given to the presentation

	private static final long serialVersionUID = 227L;

	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";

	public MenuController(Frame frame, Presentation pres)
	{
		parent = frame;
		presentation = pres;

		// The one composition point for the concrete persistence
		// mechanism. OpenCommand and SaveCommand below receive this
		// through the PresentationLoader/PresentationSaver
		// abstractions, never as a concrete XMLAccessor reference.
		XMLAccessor xmlAccessor = new XMLAccessor();

		final Command openCommand = new OpenCommand(presentation, xmlAccessor, parent);
		final Command newCommand = new NewCommand(presentation, parent);
		final Command saveCommand = new SaveCommand(presentation, xmlAccessor, parent);
		final Command exitCommand = new ExitCommand(presentation);
		final Command nextSlideCommand = new NextSlideCommand(presentation);
		final Command prevSlideCommand = new PrevSlideCommand(presentation);
		final Command gotoSlideCommand = new GotoSlideCommand(presentation);
		final Command aboutCommand = new AboutCommand(parent);

		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				openCommand.execute();
			}
		});
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				newCommand.execute();
			}
		});
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveCommand.execute();
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				exitCommand.execute();
			}
		});
		add(fileMenu);
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				nextSlideCommand.execute();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				prevSlideCommand.execute();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				gotoSlideCommand.execute();
			}
		});
		add(viewMenu);
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				aboutCommand.execute();
			}
		});
		setHelpMenu(helpMenu);        // needed for portability (Motif, etc.).
	}

	// create a menu item
	public MenuItem mkMenuItem(String name)
	{
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}


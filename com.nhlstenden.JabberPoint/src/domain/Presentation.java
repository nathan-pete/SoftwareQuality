package domain;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>domain.Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation
{
	private String showTitle; // title of the presentation
	private ArrayList<Slide> showList = null; // an ArrayList with Slides
	private int currentSlideNumber = 0; // the slidenummer of the current domain.Slide
	private List<PresentationObserver> observers = new ArrayList<PresentationObserver>(); // registered observers

	public Presentation()
	{
		clear();
	}

	public Presentation(PresentationObserver observer)
	{
		observers.add(observer);
		clear();
	}

	public int getSize()
	{
		return showList.size();
	}

	public String getTitle()
	{
		return showTitle;
	}

	public void setTitle(String nt)
	{
		showTitle = nt;
	}

	// Register an observer to be notified when the current slide changes
	public void addObserver(PresentationObserver observer)
	{
		observers.add(observer);
	}

	// give the number of the current slide
	public int getSlideNumber()
	{
		return currentSlideNumber;
	}

	// change the current slide number and notify all registered observers
	public void setSlideNumber(int number)
	{
		currentSlideNumber = number;
		for (PresentationObserver observer : observers)
		{
			observer.update(this, getCurrentSlide());
		}
	}

	// go to the previous slide unless your at the beginning of the presentation
	public void prevSlide()
	{
		if (currentSlideNumber > 0)
		{
			setSlideNumber(currentSlideNumber - 1);
		}
	}

	// go to the next slide unless your at the end of the presentation.
	public void nextSlide()
	{
		if (currentSlideNumber < (showList.size() - 1))
		{
			setSlideNumber(currentSlideNumber + 1);
		}
	}

	// Delete the presentation to be ready for the next one.
	public void clear()
	{
		showList = new ArrayList<Slide>();
		setSlideNumber(-1);
	}

	// Add a slide to the presentation
	public void append(Slide slide)
	{
		showList.add(slide);
	}

	// Get a slide with a certain slidenumber
	public Slide getSlide(int number)
	{
		if (number < 0 || number >= getSize())
		{
			return null;
		}
		return (Slide) showList.get(number);
	}

	// Give the current slide
	public Slide getCurrentSlide()
	{
		return getSlide(currentSlideNumber);
	}

	public void exit(int n)
	{
		System.exit(n);
	}
}


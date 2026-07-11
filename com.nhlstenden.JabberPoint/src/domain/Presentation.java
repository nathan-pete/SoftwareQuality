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
	private String showTitle; 
	private ArrayList<Slide> showList = null; 
	private int currentSlideNumber = 0; 
	private List<PresentationObserver> observers = new ArrayList<PresentationObserver>();

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

	public void addObserver(PresentationObserver observer)
	{
		observers.add(observer);
	}

	public int getSlideNumber()
	{
		return currentSlideNumber;
	}

	public void setSlideNumber(int number)
	{
		currentSlideNumber = number;
		for (PresentationObserver observer : observers)
		{
			observer.update(this, getCurrentSlide());
		}
	}

	public void prevSlide()
	{
		if (currentSlideNumber > 0)
		{
			setSlideNumber(currentSlideNumber - 1);
		}
	}

	public void nextSlide()
	{
		if (currentSlideNumber < (showList.size() - 1))
		{
			setSlideNumber(currentSlideNumber + 1);
		}
	}

	public void clear()
	{
		showList = new ArrayList<Slide>();
		setSlideNumber(-1);
	}

	public void append(Slide slide)
	{
		showList.add(slide);
	}

	public Slide getSlide(int number)
	{
		if (number < 0 || number >= getSize())
		{
			return null;
		}
		return (Slide) showList.get(number);
	}

	public Slide getCurrentSlide()
	{
		return getSlide(currentSlideNumber);
	}

	public void exit(int n)
	{
		System.exit(n);
	}
}


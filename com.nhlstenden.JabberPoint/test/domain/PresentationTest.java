package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PresentationTest
{
	private static class RecordingObserver implements PresentationObserver
	{
		private int callCount = 0;
		private Slide lastSlide;

		public void update(Presentation presentation, Slide slide)
		{
			callCount++;
			lastSlide = slide;
		}
	}

	private Presentation presentation;

	@BeforeEach
	public void setUp()
	{
		presentation = new Presentation();
	}

	@Test
	public void constructor_noArgs_sizeIsZero()
	{
		assertEquals(0, presentation.getSize());
	}

	@Test
	public void getTitle_afterSetTitle_returnsSameTitle()
	{
		presentation.setTitle("My Presentation");
		assertEquals("My Presentation", presentation.getTitle());
	}

	@Test
	public void append_slide_increasesSize()
	{
		presentation.append(new Slide());
		assertEquals(1, presentation.getSize());
	}

	@Test
	public void getSlide_validIndex_returnsCorrectSlide()
	{
		Slide slide = new Slide();
		slide.setTitle("First");
		presentation.append(slide);
		assertEquals(slide, presentation.getSlide(0));
	}

	@Test
	public void getSlide_negativeIndex_returnsNull()
	{
		presentation.append(new Slide());
		assertNull(presentation.getSlide(-1));
	}

	@Test
	public void getSlide_indexAtOrAboveSize_returnsNull()
	{
		presentation.append(new Slide());
		assertNull(presentation.getSlide(1));
	}

	@Test
	public void getCurrentSlide_afterSetSlideNumber_returnsCorrectSlide()
	{
		Slide first = new Slide();
		Slide second = new Slide();
		presentation.append(first);
		presentation.append(second);
		presentation.setSlideNumber(1);
		assertEquals(second, presentation.getCurrentSlide());
	}

	@Test
	public void setSlideNumber_always_notifiesAllRegisteredObservers()
	{
		RecordingObserver observerOne = new RecordingObserver();
		RecordingObserver observerTwo = new RecordingObserver();
		presentation.addObserver(observerOne);
		presentation.addObserver(observerTwo);
		presentation.append(new Slide());

		presentation.setSlideNumber(0);

		assertEquals(1, observerOne.callCount);
		assertEquals(1, observerTwo.callCount);
	}

	@Test
	public void constructor_withObserver_registersThatObserver()
	{
		RecordingObserver observer = new RecordingObserver();
		Presentation withObserver = new Presentation(observer);
		int callCountAfterConstruction = observer.callCount;
		withObserver.append(new Slide());

		withObserver.setSlideNumber(0);

		assertEquals(callCountAfterConstruction + 1, observer.callCount);
	}

	@Test
	public void nextSlide_notAtEnd_incrementsSlideNumber()
	{
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		presentation.nextSlide();

		assertEquals(1, presentation.getSlideNumber());
	}

	@Test
	public void nextSlide_atLastSlide_doesNotIncrement()
	{
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		presentation.nextSlide();

		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	public void prevSlide_notAtStart_decrementsSlideNumber()
	{
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(1);

		presentation.prevSlide();

		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	public void prevSlide_atFirstSlide_doesNotDecrement()
	{
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		presentation.prevSlide();

		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	public void clear_afterAppendingSlides_resetsSizeToZeroAndSlideNumberToMinusOne()
	{
		presentation.append(new Slide());
		presentation.append(new Slide());

		presentation.clear();

		assertEquals(0, presentation.getSize());
		assertEquals(-1, presentation.getSlideNumber());
	}
}
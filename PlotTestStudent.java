import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yousif Aluobaidy
 */
public class PlotTestStudent {
	private Plot plot1, plot2, plot3;

	@Before
	public void setUp() throws Exception {
		// This one is the main plot for encompassing
		plot1 = new Plot(2, 2, 6, 6);
		
		// This one is inside plot1
		plot2 = new Plot(3, 3, 2, 2);
		
		// This one overlaps plot1
		plot3 = new Plot(5, 5, 4, 4);
	}

	@After
	public void tearDown() throws Exception {
		plot1 = plot2 = plot3 = null;
	}

	@Test
	public void testDefaultConstructor() {
		Plot defaultPlot = new Plot();
		assertEquals(0, defaultPlot.getX());
		assertEquals(0, defaultPlot.getY());
		assertEquals(1, defaultPlot.getWidth());
		assertEquals(1, defaultPlot.getDepth());
	}
	
	@Test
	public void testParamConstructor() {
		Plot paramPlot = new Plot(1, 2, 3, 4);
		assertEquals(1, paramPlot.getX());
		assertEquals(2, paramPlot.getY());
		assertEquals(3, paramPlot.getWidth());
		assertEquals(4, paramPlot.getDepth());
	}
	
	@Test
	public void testCopyConstructor() {
		Plot copy = new Plot(plot1);
		assertEquals(2, copy.getX());
		assertEquals(2, copy.getY());
		assertEquals(6, copy.getWidth());
		assertEquals(6, copy.getDepth());
	}

	@Test
	public void testOverlaps() {
		// plot3 overlaps plot1
		assertTrue(plot1.overlaps(plot3));
		
		// plot2 is inside plot1, so it overlaps
		assertTrue(plot1.overlaps(plot2));
		
		// this plot is way outside
		Plot outsidePlot = new Plot(10, 10, 1, 1);
		assertFalse(plot1.overlaps(outsidePlot));
	}

	@Test
	public void testEncompasses() {
		// plot2 is fully inside plot1
		assertTrue(plot1.encompasses(plot2));
		
		// plot3 is NOT fully inside plot1
		assertFalse(plot1.encompasses(plot3));
		
		// plot1 should encompass itself
		assertTrue(plot1.encompasses(plot1));
	}
	
	@Test
	public void testSettersAndGetters() {
		plot1.setX(10);
		plot1.setY(11);
		plot1.setWidth(12);
		plot1.setDepth(13);
		
		assertEquals(10, plot1.getX());
		assertEquals(11, plot1.getY());
		assertEquals(12, plot1.getWidth());
		assertEquals(13, plot1.getDepth());
	}

	@Test
	public void testToString() {
		// should be "x,y,width,depth"
		assertEquals("2,2,6,6", plot1.toString());
		assertEquals("3,3,2,2", plot2.toString());
	}

}

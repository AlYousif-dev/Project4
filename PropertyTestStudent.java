import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the student test file for the Property class.
 */
public class PropertyTestStudent {
	Property prop1;

	@Before
	public void setUp() throws Exception {
		// makes a new property for testing
		prop1 = new Property("Big House", "Rockville", 2500.0, "Bob Smith", 2, 2, 4, 4);
	}

	@After
	public void tearDown() throws Exception {
		prop1 = null;
	}

	@Test
	public void testDefaultConstructor() {
		Property prop = new Property();
		assertEquals("", prop.getPropertyName());
		assertEquals("", prop.getCity());
		assertEquals(0.0, prop.getRentAmount(), 0.001);
		assertEquals("", prop.getOwner());
		// check that it made a default plot
		assertNotNull(prop.getPlot());
		assertEquals(0, prop.getPlot().getX());
	}

	@Test
	public void testParamConstructor() {
		Property prop = new Property("Small Apt", "Germantown", 1200.0, "Sue");
		assertEquals("Small Apt", prop.getPropertyName());
		assertEquals("Germantown", prop.getCity());
		assertEquals(1200.0, prop.getRentAmount(), 0.001);
		assertEquals("Sue", prop.getOwner());
		// check that this one also made a default plot
		assertNotNull(prop.getPlot());
		assertEquals(0, prop.getPlot().getX());
		assertEquals(1, prop.getPlot().getWidth());
	}
	
	@Test
	public void testFullConstructor() {
		// this one uses the setup object
		assertEquals("Big House", prop1.getPropertyName());
		assertEquals("Rockville", prop1.getCity());
		assertEquals(2500.0, prop1.getRentAmount(), 0.001);
		assertEquals("Bob Smith", prop1.getOwner());
		// check the plot
		assertEquals(2, prop1.getPlot().getX());
		assertEquals(2, prop1.getPlot().getY());
		assertEquals(4, prop1.getPlot().getWidth());
		assertEquals(4, prop1.getPlot().getDepth());
	}

	@Test
	public void testCopyConstructor() {
		Property propCopy = new Property(prop1);
		assertEquals("Big House", propCopy.getPropertyName());
		assertEquals("Rockville", propCopy.getCity());
		assertEquals(2500.0, propCopy.getRentAmount(), 0.001);
		assertEquals("Bob Smith", propCopy.getOwner());
		assertEquals(2, propCopy.getPlot().getX());
	}

	@Test
	public void testGetters() {
		// just test all the getters
		assertEquals("Big House", prop1.getPropertyName());
		assertEquals("Rockville", prop1.getCity());
		assertEquals(2500.0, prop1.getRentAmount(), 0.001);
		assertEquals("Bob Smith", prop1.getOwner());
		assertNotNull(prop1.getPlot());
	}

	@Test
	public void testToString() {
		// format: propertyName,city,owner,rentAmount
		assertEquals("Big House,Rockville,Bob Smith,2500.0", prop1.toString());
	}

}

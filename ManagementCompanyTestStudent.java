import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the student test file for the ManagementCompany class.
 */
public class ManagementCompanyTestStudent {
	ManagementCompany mgm;
	Property prop1, prop2, prop3, prop4, prop5, prop6;

	@Before
	public void setUp() throws Exception {
		// make a company that covers the whole 10x10 area
		mgm = new ManagementCompany("Cool Properties", "54321", 6.0, 0, 0, 10, 10);
		
		// make some properties to add
		prop1 = new Property("House 1", "City A", 1000, "Owner 1", 1, 1, 2, 2);
		prop2 = new Property("House 2", "City B", 2000, "Owner 2", 4, 4, 3, 3);
		prop3 = new Property("House 3", "City C", 3000, "Owner 3", 1, 4, 2, 2);
		prop4 = new Property("House 4", "City D", 4000, "Owner 4", 7, 1, 2, 2);
		prop5 = new Property("House 5", "City E", 5000, "Owner 5", 7, 4, 2, 2);
		
		// this one will be used to test the "full" case
		prop6 = new Property("House 6", "City F", 6000, "Owner 6", 7, 7, 2, 2);
	}

	@After
	public void tearDown() throws Exception {
		mgm = null;
		prop1 = prop2 = prop3 = prop4 = prop5 = prop6 = null;
	}

	@Test
	public void testAddProperty_Valid() {
		// add a few good properties
		assertEquals(0, mgm.addProperty(prop1));
		assertEquals(1, mgm.addProperty(prop2));
		assertEquals(2, mgm.addProperty(prop3));
	}
	
	@Test
	public void testAddProperty_Null() {
		// try adding a null property
		assertEquals(-2, mgm.addProperty(null));
	}

	@Test
	public void testAddProperty_Full() {
		// fill up the array
		mgm.addProperty(prop1);
		mgm.addProperty(prop2);
		mgm.addProperty(prop3);
		mgm.addProperty(prop4);
		mgm.addProperty(prop5);
		
		// array is full (max 5), should return -1
		assertEquals(-1, mgm.addProperty(prop6));
	}
	
	@Test
	public void testAddProperty_NotEncompassed() {
		// this property is outside the mgm plot (which is 0,0,10,10)
		Property outsideProp = new Property("Outside", "Far away", 100, "Me", 11, 11, 1, 1);
		assertEquals(-3, mgm.addProperty(outsideProp));
	}
	
	@Test
	public void testAddProperty_Overlaps() {
		mgm.addProperty(prop1); // add prop1 at (1,1,2,2)
		
		// this new property overlaps prop1
		Property overlapProp = new Property("Overlap", "Oops", 100, "Me", 1, 1, 1, 1);
		assertEquals(-4, mgm.addProperty(overlapProp));
	}

	@Test
	public void testRemoveLastProperty() {
		mgm.addProperty(prop1);
		mgm.addProperty(prop2);
		assertEquals(2, mgm.getPropertiesCount());
		
		mgm.removeLastProperty();
		assertEquals(1, mgm.getPropertiesCount());
		// check that the last one is now null
		assertNull(mgm.getProperties()[1]);
	}

	@Test
	public void testIsPropertiesFull() {
		assertFalse(mgm.isPropertiesFull()); // not full yet
		mgm.addProperty(prop1);
		mgm.addProperty(prop2);
		mgm.addProperty(prop3);
		mgm.addProperty(prop4);
		mgm.addProperty(prop5);
		assertTrue(mgm.isPropertiesFull()); // now it's full
	}

	@Test
	public void testGetPropertiesCount() {
		assertEquals(0, mgm.getPropertiesCount());
		mgm.addProperty(prop1);
		assertEquals(1, mgm.getPropertiesCount());
		mgm.addProperty(prop2);
		assertEquals(2, mgm.getPropertiesCount());
	}

	@Test
	public void testGetTotalRent() {
		assertEquals(0, mgm.getTotalRent(), 0.001);
		mgm.addProperty(prop1); // 1000
		mgm.addProperty(prop2); // 2000
		assertEquals(3000, mgm.getTotalRent(), 0.001);
	}

	@Test
	public void testGetHighestRentPropperty() {
		assertNull(mgm.getHighestRentPropperty()); // no properties yet
		mgm.addProperty(prop1); // 1000
		mgm.addProperty(prop2); // 2000
		mgm.addProperty(prop3); // 3000
		
		// Checks if the returned property has the correct rent amount
		assertEquals(prop3.getRentAmount(), mgm.getHighestRentPropperty().getRentAmount(), 0.001);	}

	@Test
	public void testIsManagementFeeValid_True() {
		// fee is 6.0, which is valid
		assertTrue(mgm.isMangementFeeValid());
	}
	
	@Test
	public void testIsManagementFeeValid_False() {
		// make a company with a bad fee
		ManagementCompany badFeeMgm = new ManagementCompany("Bad", "1", 101.0, 0, 0, 10, 10);
		assertFalse(badFeeMgm.isMangementFeeValid());
		
		ManagementCompany badFeeMgm2 = new ManagementCompany("Worse", "2", -1.0, 0, 0, 10, 10);
		assertFalse(badFeeMgm2.isMangementFeeValid());
	}

	@Test
	public void testConstructors() {
		// test default
		ManagementCompany mDefault = new ManagementCompany();
		assertEquals("", mDefault.getName());
		assertNotNull(mDefault.getPlot());
		assertEquals(10, mDefault.getPlot().getWidth()); // default plot is 10x10
		
		// test param
		ManagementCompany mParam = new ManagementCompany("Test Co", "999", 5.0);
		assertEquals("Test Co", mParam.getName());
		assertEquals(5.0, mParam.getMgmFeePer(), 0.01);
		
		// test copy
		ManagementCompany mCopy = new ManagementCompany(mgm);
		assertEquals("Cool Properties", mCopy.getName());
		assertEquals(6.0, mCopy.getMgmFeePer(), 0.01);
	}

	@Test
	public void testGetters() {
		assertEquals("Cool Properties", mgm.getName());
		assertEquals("54321", mgm.getTaxID());
		assertEquals(6.0, mgm.getMgmFeePer(), 0.01);
		assertNotNull(mgm.getPlot());
		assertNotNull(mgm.getProperties());
	}

	@Test
	public void testToString() {
		mgm.addProperty(prop1); // 1000 rent
		mgm.addProperty(prop2); // 2000 rent
		// total rent = 3000. fee is 6%. total fee = 180.0
		
		String str = mgm.toString();
		System.out.println(str); // for debugging
		
		assertTrue(str.contains("Cool Properties"));
		assertTrue(str.contains("54321"));
		assertTrue(str.contains("House 1,City A,Owner 1,1000.0"));
		assertTrue(str.contains("House 2,City B,Owner 2,2000.0"));
		assertTrue(str.contains("total management Fee: 180.0"));
	}
}

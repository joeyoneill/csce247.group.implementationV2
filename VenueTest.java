import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VenueTest {

    @BeforeClass
    public static void oneTimeSetup() {

    }

    @AfterClass
    public static void oneTimeTearDown() {

    }

    @Before
    public void setup() {
        // runs before each test
    }

    @After
    public void tearDown() {
        // runs after each test
    }

    @Test
    public void venueConstructor() {
        Venue venue = new Venue("name", 4, 5); // venue with name of name, rows 4 and columns 5
        assertEquals("name", venue.getName());
        assertEquals(4, venue.getRows());
        assertEquals(5, venue.getColumns());
    }

}
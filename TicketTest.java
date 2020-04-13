import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TicketTest {

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
    public void testTicketRefund() {
        ArrayList<Event> events = new ArrayList<Event>();
        ArrayList<User> users = new ArrayList<User>();
        Database database = new Database();
        database.load(events, users);
        Ticket ticket = new Ticket("username", database.findEvent("Frozen 2", events), LocalTime.now(), LocalDate.now(),
                2, 3, true);
        assertEquals(true, ticket.isRefundable());
    }
}
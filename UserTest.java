import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {

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
    public void testDefault() {
        User user = new User();
        assertEquals("Guest", user.getName());
    }

    @Test
    public void testSetUser() {
        User user = new User();
        user.setName("Luke");
        assertEquals("Luke", user.getName());
    }

    @Test
    public void testSetPass() {
        User user = new User();
        user.setPassword("password");
        ;
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetUserType() {
        User user = new User();
        user.setUsertype(UserType.CHILD);
        assertEquals(UserType.CHILD, user.getUsertype());
    }

}
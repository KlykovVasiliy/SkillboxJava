import junit.framework.TestCase;
import org.junit.Before;

public class AccountTest extends TestCase {
    private Account accountTest1;

    @Override
    @Before
    protected void setUp() throws Exception {
        accountTest1 = Account.createAnAccount("Test1", 292000);
    }

    public void testSetBlockedAccount() {
        accountTest1.setBlockedAccount();
        boolean expected = accountTest1.isNotBlockedAccount();
        assertFalse(expected);
    }

    public void testGetAccNumber() {
        String expected = "Test1";
        String actual = accountTest1.getAccNumber();
        assertEquals(expected, actual);
    }
    public void testGetMoney() {
        long expected = 292000;
        long actual = accountTest1.getMoney();
        assertEquals(expected, actual);
    }

    public void testGetIsBlockedAccount() {
        boolean expected = accountTest1.isNotBlockedAccount();
        assertTrue(expected);
    }

    public void testSetMoney() {
        accountTest1.setMoney(300000);
        long expected = accountTest1.getMoney();
        long actual = 300000;
        assertEquals(expected, actual);
    }
}
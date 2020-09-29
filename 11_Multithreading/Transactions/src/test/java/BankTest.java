import junit.framework.TestCase;
import org.junit.Before;

import java.util.Hashtable;

public class BankTest extends TestCase {
    private Bank bank;
    private Hashtable<String, Account> bankMap;

    private Account accountTest1 = Account.createAnAccount("Test1", 376000);
    private Account accountTest2 = Account.createAnAccount("Test2", 24000);

    @Override
    @Before
    protected void setUp() {
        bank = new Bank();
        bankMap = bank.getAccounts();
        bankMap.put(accountTest1.getAccNumber(), accountTest1);
        bankMap.put(accountTest2.getAccNumber(), accountTest2);
    }

    public void testTransfer() {
        long amount = 160000;
        bank.transfer(accountTest1.getAccNumber(), accountTest2.getAccNumber(), amount);
        long expected;
        long actual;
        if (accountTest1.isNotBlockedAccount()) {
            expected = accountTest1.getMoney();
            actual = 216000;
        } else {
            expected = accountTest1.getMoney();
            actual = 376000;
        }
        assertEquals(expected, actual);
    }

    public void testGetBalance() {
        long expected = bank.getBalanceOfAccount(bankMap.get(accountTest1.getAccNumber()).getAccNumber());
        long actual = 376000;
        assertEquals(expected, actual);
    }

    public void testGetSumAllMoneyOfBank() {
        long expected = bank.getSumAllMoneyOfBank();
        long actual = 400000;
        assertEquals(expected, actual);
    }
}
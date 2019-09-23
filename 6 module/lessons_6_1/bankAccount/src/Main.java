import accounts.CardAccount;
import accounts.CheckingAccount;
import accounts.DepositAccount;

public class Main {
    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount();
        DepositAccount depositAccount = new DepositAccount();
        CardAccount cardAccoutn = new CardAccount();

        account.deposit(150);
        account.withdraw(200);
        account.withdraw(100);
        account.deposit(-5);
        account.withdraw(-10);
        System.out.println("================================================");

        depositAccount.deposit(500);
        depositAccount.withdraw(100);
        depositAccount.deposit(0);
        depositAccount.deposit(-5);
        System.out.println("================================================");

        cardAccoutn.deposit(1000);
        cardAccoutn.withdraw(200);
        cardAccoutn.deposit(-50);
        cardAccoutn.withdraw(-10);
    }
}
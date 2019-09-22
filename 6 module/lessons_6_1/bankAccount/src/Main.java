import accounts.CardAccount;
import accounts.CheckingAccount;
import accounts.DepositAccount;

public class Main {
    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount();
        DepositAccount depositAccount = new DepositAccount();
        CardAccount cardAccoutn = new CardAccount();

        account.isDeposit(150);
        account.isWithdraw(200);
        account.isWithdraw(100);
        account.isDeposit(-5);
        account.isWithdraw(-10);
        System.out.println("================================================");

        depositAccount.isDeposit(500);
        depositAccount.isWithdraw(100);
        depositAccount.isDeposit(0);
        depositAccount.isDeposit(-5);
        System.out.println("================================================");

        cardAccoutn.isDeposit(1000);
        cardAccoutn.isWithdraw(200);
        cardAccoutn.isDeposit(-50);
        cardAccoutn.isWithdraw(-10);
    }
}
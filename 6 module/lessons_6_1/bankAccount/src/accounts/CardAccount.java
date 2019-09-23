package accounts;

public class CardAccount extends CheckingAccount {
    private static final double COMISSION = 0.01;

    @Override
    public boolean withdraw(double money) {              //снятие со счета
        return super.withdraw(money * (1 + COMISSION));
    }
}
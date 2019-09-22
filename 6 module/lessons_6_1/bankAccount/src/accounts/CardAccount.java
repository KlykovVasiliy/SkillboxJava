package accounts;

public class CardAccount extends CheckingAccount {
    private static final double COMISSION = 0.01;

    @Override
    public boolean isWithdraw(double money) {              //снятие со счета
        return super.isWithdraw(money * (1 + COMISSION));
    }
}
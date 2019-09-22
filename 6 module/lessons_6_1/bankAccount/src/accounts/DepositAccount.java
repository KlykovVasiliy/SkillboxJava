package accounts;

import java.util.Calendar;

public class DepositAccount extends CheckingAccount {
    private Calendar calendarStart = Calendar.getInstance();
    private Calendar aMounthLater = Calendar.getInstance();
    private Calendar dateDeleteMoney;

    private boolean isWithdraw() {
        return aMounthLater.before(dateDeleteMoney);
    }

    @Override
    public boolean isDeposit(double money) {
        boolean isAddDate = super.isDeposit(money);
        if (isAddDate) {
            aMounthLater.add(Calendar.MONTH, 1);        //месяц спустя когда можно снимать деньги
            System.out.println(aMounthLater.getTime());
        }
        return isAddDate;
    }

    @Override
    public boolean isWithdraw(double money) {
        boolean isWithdrawMoney = super.isWithdraw(money);
        if (isWithdrawMoney) {
            dateDeleteMoney = Calendar.getInstance();           //дата когда была попытка снять деньги
            if (!isWithdraw()) {
                System.err.printf("Деньги будут возвращены обратно на счёт, т.к. 1 месяц после " +
                        "последнего пополнения %s ещё не прошел%n", calendarStart.getTime());
                isDeposit(money);
                return false;
            }
        }
        return isWithdrawMoney;
    }
}
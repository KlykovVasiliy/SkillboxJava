package accounts;

import java.util.Calendar;

public class DepositAccount extends CheckingAccount {
    private Calendar calendarStart = Calendar.getInstance();
    private Calendar aMounthLater = Calendar.getInstance();
    private Calendar dateDeleteMoney;

    private boolean isMonthPassed() {
        return aMounthLater.after(dateDeleteMoney);
    }

    @Override
    public boolean deposit(double money) {
        boolean isAddDate = super.deposit(money);
        if (isAddDate) {
            aMounthLater.add(Calendar.MONTH, 1);        //месяц спустя когда можно снимать деньги
            System.out.println("Денежные средства можете снять не раньше " +
                    aMounthLater.getTime());
        }
        return isAddDate;
    }

    @Override
    public boolean withdraw(double money) {
        dateDeleteMoney = Calendar.getInstance();
        if (isMonthPassed()) {
            System.err.printf("В снятии денег со счёта будет отказано, т.к. 1 месяц после " +
                            "последнего пополнения %s ещё не прошел%nТекущая дата %s%n",
                    calendarStart.getTime(), Calendar.getInstance().getTime());
            return false;
        } else {
            return super.withdraw(money);
        }
    }
}
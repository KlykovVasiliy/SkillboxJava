package accounts;

import java.util.Calendar;

public class DepositAccount extends CheckingAccount {
    private Calendar calendarStart;
    private Calendar dateDeleteMoney;
    private Calendar aMounthLater;

    private boolean isWithdraw() {
        return aMounthLater.before(dateDeleteMoney);
    }

    @Override
    public boolean isDeposit(double money) {
        double moneyStart = getScore();
        setScore(getScore() + money);
        boolean isSuccess = false;
        if (moneyStart < getScore()) {
            isSuccess = true;
            System.out.printf("На ваш счёт поступило %.02f рубль(ей).%n", money);
            calendarStart = Calendar.getInstance();             //дата пополнения
            aMounthLater = Calendar.getInstance();
            aMounthLater.add(Calendar.MONTH, 1);        //месяц спустя когда можно снимать деньги
        } else {
            System.err.println("Не удалось пополнить счёт.");
        }
        balance();
        return isSuccess;
    }

    @Override
    public boolean isWithdraw (double money) {
        dateDeleteMoney = Calendar.getInstance();           //дата когда была попытка снять деньги
        boolean isSuccess = false;
        if (isWithdraw()) {
            if (money <= getScore()) {
                isSuccess = true;
                setScore(getScore() - money);
                balance();
            } else {
                System.err.printf("На расчетном счету недостаточно денежных средств для снятия.%n" +
                        "Баланс вашего счета составляет %.02f%n", getScore());
            }
        } else {
            System.err.printf("Вы не можете снять деньги со счета. 1 месяц после последнего " +
                    "пополнения %s ещё не прошел", calendarStart.getTime());
        }
        return isSuccess;
    }
}
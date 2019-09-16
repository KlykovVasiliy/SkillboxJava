package accounts;

import java.util.Calendar;

public class DepositAccount extends CheckingAccount {
    private Calendar calendarStart;
    private Calendar dateDeleteMoney;
    private Calendar aMounthLeter;


    private boolean isDeleteMoney() {
        return aMounthLeter.before(dateDeleteMoney);
    }

    @Override
    public void addMoney(double money) {
        setScore(getScore() + money);
        System.out.printf("На ваш счёт поступило %.02f рубль(ей).%n", money);
        outputBalance();
        calendarStart = Calendar.getInstance();
        aMounthLeter = Calendar.getInstance();
        aMounthLeter.add(Calendar.MONTH, 1);
    }

    @Override
    public void deleteMoney (double money) {              //снятие со счета
        dateDeleteMoney = Calendar.getInstance();
        if (isDeleteMoney()) {
            if (money <= getScore()) {
                setScore(getScore() - money);
                outputBalance();
            } else {
                System.out.printf("На расчетном счету недостаточно денежных средств для снятия.%n" +
                        "Баланс вашего счета составляет %.02f%n", getScore());
            }
        } else {
            System.err.printf("Вы не можете снять деньги со счета. 1 месяц после последнего " +
                    "пополнения %s ещё не прошел", calendarStart.getTime());
        }

    }
}

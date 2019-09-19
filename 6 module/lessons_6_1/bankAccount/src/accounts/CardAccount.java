package accounts;

public class CardAccount extends CheckingAccount {

    @Override
    public boolean isWithdraw (double money) {              //снятие со счета
        boolean isSuccess = false;
        if (money <= getScore()) {
            isSuccess = true;
            setScore(getScore() - money);
            money *= 0.99;
            System.out.printf("Снято с карточного счета %.02f", money);
            balance();
        } else {
            System.err.printf("На расчетном счету недостаточно денежных средств для снятия.%n" +
                    "Баланс вашего счета составляет %.02f%n", getScore());
        }
        return isSuccess;
    }
}
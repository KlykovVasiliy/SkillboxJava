package accounts;

public class CardAccoutn extends CheckingAccount {


    @Override
    public void deleteMoney (double money) {              //снятие со счета
        if (money <= getScore()) {
            setScore(getScore() - money);
            money *= 0.99;
            System.out.printf("Снято с карточного счета %.02f", money);
            outputBalance();
        } else {
            System.out.printf("На расчетном счету недостаточно денежных средств для снятия.%n" +
                    "Баланс вашего счета составляет %.02f%n", getScore());
        }
    }

}

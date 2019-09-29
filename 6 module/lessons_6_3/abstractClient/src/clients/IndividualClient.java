package clients;

public class IndividualClient extends Client {
    @Override
    public void balance() {
        System.out.printf("Баланс лицевого счета составляет: %.2f рубль(ей).%n", paymentAccount);
    }

    @Override
    public void withdrawal(double money) {
        if (money <= 0 || money > paymentAccount) {
            System.err.printf("Желаемая сумма для снятия должна быть больше 0 и меньше либо " +
                    "равной %.2f рубль(ей).%nБыла попытка снять %.2f.%n", paymentAccount, money);
        } else {
            paymentAccount -= money;
            balance();
        }
    }

    @Override
    public void replenish(double money) {
        if (money <= 0) {
            System.err.println("Лицевой счет не может быть пополнен. Вносимая сумма должна " +
                    "быть больше 0.");
        } else {
            paymentAccount += money;
            balance();
        }
    }
}
package clients;

public abstract class Client {
    protected double paymentAccount;

    public void balance() {
        System.out.printf("Баланс лицевого счета составляет: %.2f рубль(ей).%n", paymentAccount);
    }

    public void withdrawal(double money) {
        if (money <= 0 || money > paymentAccount) {
            System.err.printf("Желаемая сумма для снятия должна быть больше 0 и меньше либо " +
                    "равной %.2f рубль(ей).%nБыла попытка снять %.2f.%n", paymentAccount, money);
        } else {
            paymentAccount -= money;
            balance();
        }
    }

    public void replenish(double money) {
        if (money <= 0) {
            System.err.println("Лицевой счет не может быть пополнен. Вносимая сумма должна " +
                    "быть больше 0.");
        } else {
            paymentAccount += money;
            System.out.println("Лицевой счёт пополнен.");
            balance();
        }
    }
}
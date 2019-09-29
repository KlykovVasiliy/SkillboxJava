package clients;

public abstract class Client {
    protected double paymentAccount;

    public abstract void balance();

    public abstract void withdrawal(double money);

    public abstract void replenish(double money);
}
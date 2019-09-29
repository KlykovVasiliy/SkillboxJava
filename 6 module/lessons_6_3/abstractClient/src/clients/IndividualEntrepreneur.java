package clients;

public class IndividualEntrepreneur extends IndividualClient {
    @Override
    public void replenish(double money) {
        super.replenish(money);
        if (money < 1000 && money > 0) {
            paymentAccount -= money * 0.99;
        } else {
            paymentAccount -= money * 0.995;
        }
    }
}
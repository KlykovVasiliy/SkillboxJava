package clients;

public class IndividualEntrepreneur extends Client {
    private static final double COMISSION_ELSE_MONEY_LESS_THOUSAND = 0.01;
    private static final double COMISSION_ELSE_MONEY_MORE_THOUSAND = 0.005;

    @Override
    public void replenish(double money) {
        if (money < 1000 && money > 0) {
            super.replenish(money - (money * COMISSION_ELSE_MONEY_LESS_THOUSAND));
        } else if (money >= 1000) {
            super.replenish(money - (money * COMISSION_ELSE_MONEY_MORE_THOUSAND));
        }
    }
}
package clients;

public class LegalEntity extends Client {
    private static final double COMISSION = 0.01;

    @Override
    public void withdrawal(double money) {
        super.withdrawal(money * (1 + COMISSION));
    }
}
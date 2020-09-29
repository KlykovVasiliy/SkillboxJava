import lombok.Getter;
import lombok.Setter;

public class Account {
    @Getter
    private String accNumber;
    @Getter
    @Setter
    private long money;
    @Getter
    private boolean isNotBlockedAccount;

    private Account(String accNumber, long money, boolean isNotBlockedAccount) {
        this.accNumber = accNumber;
        this.money = money;
        this.isNotBlockedAccount = isNotBlockedAccount;
    }
    public static Account createAnAccount(String accNumber, long money) {
        return new Account(accNumber, money, true);
    }

    public void setBlockedAccount() {
        isNotBlockedAccount = false;
    }
}
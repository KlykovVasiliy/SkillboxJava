import lombok.Getter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Bank {
    private final Random random = new Random();
    @Getter
    private volatile Hashtable<String, Account> accounts;
    @Getter
    protected AtomicInteger theNumberOfAllTransactions;
    @Getter
    protected AtomicInteger countOverFiftyThousand;

    public Bank() {
        accounts = new Hashtable<>();
        theNumberOfAllTransactions = new AtomicInteger();
        countOverFiftyThousand = new AtomicInteger();
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        System.out.printf(ColorSchema.WHITE + "Перевод суммы %d со счета %s на счет %s.%n",
                amount, fromAccountNum, toAccountNum);
        boolean accountsNotEquals = !fromAccountNum.equals(toAccountNum);
        printInfoTransferOnThisAccount(!accountsNotEquals);
        if (amount > 50000 && accountsNotEquals) {
            transferBigSum(fromAccountNum, toAccountNum, amount);
        } else if (0 <= amount && amount < 50000 && accountsNotEquals) {
            startMoneyTransfer(fromAccountNum, toAccountNum, amount);
        }
    }

    private void transferBigSum(String fromAccountNum, String toAccountNum, long amount) {
        boolean isBlockedAccount = true;
        try {
            isBlockedAccount = isFraud(fromAccountNum, toAccountNum, amount);
            blockAccounts(isBlockedAccount, fromAccountNum, toAccountNum);                       //блокировка счетов
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!isBlockedAccount) {
            double fivePercent = 0.05;
            double availableFivePercentOfTheAll = Math.floor(
                    theNumberOfAllTransactions.intValue() * fivePercent);
            if (availableFivePercentOfTheAll >= countOverFiftyThousand.intValue()) {
                countPlusOne(startMoneyTransfer(fromAccountNum, toAccountNum, amount));
            } else {
                printLimitTransferBigSum();
            }
        } else {
            printAccountBlock(fromAccountNum, toAccountNum);
        }
    }

    private void countPlusOne(boolean bol) {
        if (bol) {
            countOverFiftyThousand.incrementAndGet();
        }
    }

    private boolean startMoneyTransfer(String fromAccountNum, String toAccountNum, long amount) {
        boolean notBlockedFromAccount = accounts.get(fromAccountNum).isNotBlockedAccount();         //true
        boolean notBlockedToAccount = accounts.get(toAccountNum).isNotBlockedAccount();             //false
        boolean isAllowTransfer = true;
        if (notBlockedFromAccount && notBlockedToAccount) {
            long moneyAnFromAccount = accounts.get(fromAccountNum).getMoney();
            if (moneyAnFromAccount >= amount) {
                accounts.get(fromAccountNum).setMoney(moneyAnFromAccount - amount);
                accounts.get(toAccountNum)
                        .setMoney(accounts.get(toAccountNum).getMoney() + amount);
                theNumberOfAllTransactions.incrementAndGet();
                printTransferMoney(amount, fromAccountNum, toAccountNum);
            } else {
                isAllowTransfer = false;
                printNotMoneyOnTheAccount(fromAccountNum);
            }
        } else {
            isAllowTransfer = false;
            printAccountBlock(fromAccountNum, toAccountNum);
        }
        return isAllowTransfer;
    }

    private void blockAccounts(boolean isBlockedAccounts, String fromAccountNum,
                               String toAccountNum) {
        if (isBlockedAccounts) {
            accounts.get(fromAccountNum).setBlockedAccount();
            accounts.get(toAccountNum).setBlockedAccount();
        }
    }

    private void printAccountBlock(String from, String to) {
        System.out.printf(ColorSchema.RED + "Счета %s и %s заблокированы%n", from, to);
    }

    private void printNotMoneyOnTheAccount(String str) {
        System.out.printf(ColorSchema.RED + "Недостаточно денег на счете отправителя %s.%n", str);
    }

    private void printLimitTransferBigSum() {
        System.out.printf(ColorSchema.WHITE + "Установлен лимит на перевод больших сумм.%n");
    }

    private void printInfoTransferOnThisAccount(boolean bol) {
        if (bol) {
            System.out.printf(ColorSchema.RED + "Перевод на этот же счет не допустим.%n");
        }
    }

    private void printTransferMoney(long amount, String fromAccountNum, String toAccountNum) {
        System.out.printf(ColorSchema.GREEN + "Сумма %d переведена.%n", amount);
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalanceOfAccount(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllMoneyOfBank() {
        return Stream.of(accounts)
                .map(Map::values)
                .flatMap(a -> a.stream()
                        .map(Account::getMoney)).reduce((long) 0, Long::sum);
    }

    public void addAccountsInBank(int countAccounts) {
        for (int i = 0; i < countAccounts; i++) {
            String bankAccount = "account ";
            String nameAccount = bankAccount + i;
            accounts.put(nameAccount,
                    Account.createAnAccount(nameAccount, generateBalanceForAccount()));
        }
    }

    private long generateBalanceForAccount() {
        int minBalance = 30000;
        int maxBalance = 100000;
        return (long) (Math.random() * ++maxBalance) + minBalance;
    }

    public String getRandomNameAccount() {
        int countAccountsInBank = accounts.size() - 1;
        int randomSelect = (int) (Math.random() * ++countAccountsInBank);
        List<String> listKey = new ArrayList<>(accounts.keySet());
        return listKey.get(randomSelect);
    }
}
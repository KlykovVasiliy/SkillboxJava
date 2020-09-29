import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO: после того как программа будет написана, произвести рефакторинг по цвету отображения текста в консоли.
 */
public class Main {
    private static ThreadPoolExecutor executor;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        int coreCount = Runtime.getRuntime().availableProcessors();
        int coreCount = 10;
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(coreCount);

        Bank bank = new Bank();
        bank.addAccountsInBank(20);
        System.out.printf(ColorSchema.WHITE + "Всего денег в банке на счетах: %d%n",
                bank.getSumAllMoneyOfBank());

        List<Thread> threadList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            threadList.add(new Thread(() -> {
                createdRunnableObjectThread(bank);
            }));
        }
        runExecutorsTransfer(threadList);



        System.out.printf(ColorSchema.WHITE + "Всего денег в банке на счетах: %d.%n" +
                        "Произведено всего %d транзакций из них %d номиналом более %d%n",
                bank.getSumAllMoneyOfBank(), bank.getTheNumberOfAllTransactions().intValue(),
                bank.getCountOverFiftyThousand().intValue(), 50000);
        long end = System.currentTimeMillis();
        System.out.printf("Работа программы заняла %d мс%n", end - start);
    }


    private static void createdRunnableObjectThread(Bank bank) {
        for(int i = 0; i < 500; i++) {
            bank.transfer(bank.getRandomNameAccount(), bank.getRandomNameAccount(),
                    randomSumForTransfer());
        }
    }

    private static void runExecutorsTransfer(List<Thread> threadList) {
        for (Thread thread : threadList) {
            executor.execute(thread);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static long randomSumForTransfer() {
//        int maxTransfer = 60000;
        int maxTransfer = 40000;
        return (long) (Math.random() * ++maxTransfer);
    }
}
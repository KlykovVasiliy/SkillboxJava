public class Main {
    public static double score = 0.00;
    public static void main(String[] args) {

    }

    private static void replenishAccount(double money) {
        score = score + money;
        System.out.printf("На ваш счёт поступило %.02f рубль(ей).%n", score);
        outputBalace();
    }

    private static void outputBalace () {
        System.out.printf("Баланс вашего счёта составляет %.02f рубля(ей)%n", score);
    }
}

public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        text = text.replaceAll("[^0-9,]+", " ");
        String fragments[] = text.split("\\s,");

        int sum = 0;
        for (int a = 0; a < fragments.length; a++) {
            sum += Integer.parseInt(fragments[a].trim());
        }
        System.out.println("Заработали все вместе: " + sum + " рубля(ей).");
    }
}
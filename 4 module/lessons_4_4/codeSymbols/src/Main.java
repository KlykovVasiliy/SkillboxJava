public class Main {
    public static void main(String[] args) {
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for(int a = 0; a < text.length(); a++) {
            char ch = text.charAt(a);
            int chCode = (int) ch;
            int nextString = a % 8;
            if(nextString == 0) {
                System.out.print("\n");
            }
            System.out.print("Код буквы: " + ch + " - " + chCode + "; ");
        }
    }
}

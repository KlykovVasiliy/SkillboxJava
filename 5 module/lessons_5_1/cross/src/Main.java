public class Main {
    public static void main(String[] args) {
        String text = "X";
        String space = " ";
        String[][] str = new String[7][7];

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[i].length; j++) {
                if ((i == j) || (j == str[i].length - i - 1)) {
                    str[i][j] = text;
                } else {
                    str[i][j] = space;
                }
            }
        }

        for (int a = 0; a < str.length; a++) {
            for (int b = 0; b < str[a].length; b++) {
                System.out.print(str[a][b]);
            }
            System.out.println();
        }
    }
}
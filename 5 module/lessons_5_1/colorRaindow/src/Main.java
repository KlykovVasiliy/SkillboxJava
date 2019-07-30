public class Main {
    public static void main(String[] args) {
        String text = "Красный оранжевый желтый зеленый голубой синий фиолетовый";
        String[] colors = text.split(",?\\s+");

        for (int i = 0; i < colors.length; i++) {           //вывод на печать в обычной
            System.out.println(colors[i]);                  //последовательности
        }
        System.out.println();

        for (int a = 0; a < colors.length / 2; a++) {       //переворот массива
            String tempText = colors[a];
            colors[a] = colors[colors.length - a - 1];
            colors[colors.length - a - 1] = tempText;
        }

        for (int c = 0; c < colors.length; c++) {           //вывод на печать в обратной
            System.out.println(colors[c]);                  //последовательности
        }
    }
}

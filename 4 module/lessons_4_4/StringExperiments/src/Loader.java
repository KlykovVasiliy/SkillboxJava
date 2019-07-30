public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        int firstZp = Integer.parseInt(text.substring(text.indexOf("тал") + 4, (text.indexOf("руб"))).trim());                  //создание подстроки и преобразование из строки в число типа int
        int secondZp = Integer.parseInt(text.substring(text.lastIndexOf("-") + 1, text.lastIndexOf("руб")).trim());    //создание подстроки и преобразование из строки в число типа int
        int sumZp = firstZp + secondZp;
        System.out.println(sumZp + " руб заработали Вася и Маша");
    }
}
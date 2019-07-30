public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        System.out.println(sumDigits(container.count));
    }

    public static Integer sumDigits(int number)
    {
        //@TODO: write code here
        String num = Integer.toString(number);
        int sum = 0;
        for(int i = 0; i < num.length(); i++) {
            sum += Character.getNumericValue(num.charAt(i));
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        byte minByte = Byte.MIN_VALUE;
        byte maxByte = Byte.MAX_VALUE;

        short minShort = Short.MIN_VALUE;
        short maxShort = Short.MAX_VALUE;

        int minInt = Integer.MIN_VALUE;
        int maxInt = Integer.MAX_VALUE;

        long minLong = Long.MIN_VALUE;
        long maxLong = Long.MAX_VALUE;

        float minFloat = Float.MIN_VALUE;
        float maxFloat = Float.MAX_VALUE;

        double minDouble = Double.MIN_VALUE;
        double maxDouble = Double.MAX_VALUE;

        System.out.println("Число типа byte имеет значении от минимального " + minByte + " до максимального " + maxByte);
        System.out.println("Число типа short имеет значении от минимального " + minShort + " до максимального " + maxShort);
        System.out.println("Число типа int имеет значении от минимального " + minInt + " до максимального " + maxInt);
        System.out.println("Число типа long имеет значении от минимального " + minLong + " до максимального " + maxLong);
        System.out.println("Число типа float имеет значении от минимального " + minFloat + " до максимального " + maxFloat);
        System.out.println("Число типа double имеет значении от минимального " + minDouble + " до максимального " + maxDouble);
    }
}

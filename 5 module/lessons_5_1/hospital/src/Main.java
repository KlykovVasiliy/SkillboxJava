public class Main {
    public static double generationTemperature() {
        double minTempr = 32.0;
        double maxTempr = 40.0;
        maxTempr -= minTempr;

        double temper = minTempr + (Math.random() * maxTempr);
        temper = Math.round(temper * 10) / 10.0;
        return temper;
    }

    public static void main(String[] args) {
        double[] temperature = new double[30];
        double sumTempr = 0.0;
        int goodPatiintCount = 0;

        for (int i = 0; i < temperature.length; i++) {
            temperature[i] = generationTemperature();
            sumTempr += temperature[i];
            System.out.printf("Температура %dго пациента %.1f градусов%n", i + 1, temperature[i]);

            if (temperature[i] >= 36.2 && temperature[i] <= 36.9) {
                goodPatiintCount++;
            }
        }

        System.out.println();
        double midlleTempr = sumTempr / temperature.length;
        System.out.printf("Средняя температура пациентов " +
                "по больнице %.1f градусов Цельсия.%n", midlleTempr);
        System.out.println();
        System.out.println("Количество здоровых пациентов " + goodPatiintCount);
    }
}
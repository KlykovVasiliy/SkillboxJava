import java.util.*;

import static tasks.Task01StringStreams.*;
import static tasks.Task02Reduce.multiply;
import static tasks.Task03TruckTypes.*;

class Main {
    public static void main(String[] args) {
        //Задание 1
        System.out.printf("Задание 1.%n");
        String text = "ТУЗИК яростНО порвал грелку да ОСТАЛИСЬ от неё ласКУТКИ.";
        System.out.println(countLowercaseLetters(text));
        System.out.println(replaceWordsOnLength(text));

        //Задание 2
        System.out.printf("%nЗадание 2.%n");
        List<Integer> listOfNumbers = new ArrayList<>(10);
        for (int i = 0; i <= generatedRnd(5); i++) {
            listOfNumbers.add(generatedRnd());
        }
        System.out.println(Arrays.toString(listOfNumbers.toArray()));
        System.out.println(multiply(listOfNumbers));

        //Задание 3
        System.out.printf("%nЗадание 3.%nВозвращение типа грузовика.%n");
        Truck tr = new Truck(1000);
        System.out.println(getTypeByWeight(tr));

        List<Truck> listOfTrucks = new ArrayList<>(5);
        Truck tr2 = new Truck(5000);
        Truck tr3 = new Truck(5100);
        Truck tr4 = new Truck(20000);
        listOfTrucks.add(tr2);
        listOfTrucks.add(tr3);
        listOfTrucks.add(tr4);

        System.out.printf("%nГруппировка грузовиков по грузоподъёмности.%n");
        groupTrucksByType(listOfTrucks).entrySet().stream()
                .map(x -> x.getKey() + " => " + x.getValue())
                .forEach(System.out::println);

        System.out.printf("%nКоличество грузовиков в каждой группе.%n");
        countTrucksByType(listOfTrucks).entrySet().stream()
                .map(x -> x.getKey() + " => " + x.getValue())
                .forEach(System.out::println);
    }
    public static int generatedRnd() {
        int max = 10;
        return (int) (Math.random() * ++max);
    }

    public static int generatedRnd(int num) {
        return (int) (Math.random() * ++num);
    }
}
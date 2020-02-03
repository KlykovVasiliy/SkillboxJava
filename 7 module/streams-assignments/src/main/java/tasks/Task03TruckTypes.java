package tasks;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

/**
 * Есть грузовик Truck, у которого задана максимальная грузоподьемность.
 *
 * Грузовики делятся на 3 типа в зависимости от грузоподьемности:
 *  - Pickup        - до 2 тонн
 *  - SmallBoxTruck - до 12 тонн
 *  - SemiTrailer   - до 20 тонн
 */
public class Task03TruckTypes {

    /**
     * Функция, которая по грузовику вернет его тип.
     *
     * Пример:
     *   Truck(1_000) -> Pickup
     *
     * @param t
     * @return
     */
    public static TruckType getTypeByWeight(Truck t) {
//        throw new PleaseImplementMeException();
        return  Arrays.stream(TruckType.values())
                .filter(tr -> (tr.canHandleWeight(t.maxWeightKg)))
                .findAny()
                .get();
    }

    /**
     * Сгруппировать все грузовики по их грузоподьемности.
     *
     * Пример:
     *      List(Truck(5_000), Truck(5_100), Truck(20_000))
     *      ->
     *      Map [
     *          SmallBoxTruck => List(Truck(5_000), Truck(5_100))
     *          SemiTrailer   => List(Truck(20_000))
     *      ]
     *
     * Понадобиться:
     *   - Stream::collect
     *   - Collectors.groupingBy
     *
     * @param trucks
     * @return
     */
    public static Map<TruckType, List<Truck>> groupTrucksByType(List<Truck> trucks) {
        return trucks.stream()
                .collect(Collectors.groupingBy(x -> Arrays.stream(TruckType.values())
                            .filter(tr -> (tr.canHandleWeight(x.maxWeightKg)))
                            .findAny()
                            .get()));
    }

    /**
     * Посчитать кол-во грузовиков в каждой группе.
     *
     * Пример:
     *      List(Truck(5_000), Truck(5_100), Truck(20_000))
     *      ->
     *      Map [
     *          SmallBoxTruck => 2
     *          SemiTrailer   => 1
     *      ]
     *
     * Понадобиться:
     *   - Stream::collect
     *   - Collectors.groupingBy
     *   - Collectors.counting
     *
     * @param trucks
     * @return
     */
    public static Map<TruckType, Long> countTrucksByType(List<Truck> trucks) {
//        throw new PleaseImplementMeException();
        return trucks.stream()
                .collect(Collectors.groupingBy(x -> Arrays.stream(TruckType.values())
                .filter(tr -> (tr.canHandleWeight(x.maxWeightKg)))
                .findAny()
                        .get(), counting()));
    }

    /**
     * Грузовик и его грузоподьемность.
     */
    public static class Truck {
        int maxWeightKg;
        public Truck(int maxWeightKg) {
            this.maxWeightKg = maxWeightKg;
        }
    }

    /**
     * Тип грузовика по грузоподьемности.
     */
    public enum TruckType {
        Pickup(2000),
        SmallBoxTruck(12000),
        SemiTrailer(20000);

        private int upperBoundWeightKg;

        TruckType(int upperBoundWeightKg) {
            this.upperBoundWeightKg = upperBoundWeightKg;
        }

        public boolean canHandleWeight(int weight) {
            return weight <= this.upperBoundWeightKg;
        }
    }
}
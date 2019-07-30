import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController
{
    private static double passengerCarMaxWeight = 3500.0; // kg                 //создание переменной
    private static int passengerCarMaxHeight = 2000; // mm                      //создание переменной
    private static int controllerMaxHeight = 3500; // mm                        //создание переменной

    private static int passengerCarPrice = 100; // RUB                          //создание переменной
    private static int cargoCarPrice = 250; // RUB                              //создание переменной
    private static int vehicleAdditionalPrice = 200; // RUB                     //создание переменной

    public static void main(String[] args)
    {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in);
        int carsCount = scanner.nextInt();                                      //создание переменной

        for(int i = 0; i < carsCount; i++)
        {
            Car car = Camera.getNextCar();
            System.out.println(car);

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car);                                    //создание переменной
            if(price == -1) {
                continue;
            }

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car)
    {
        int carHeight = car.height;                                             //создание переменной
        int price = 0;                                                          //создание переменной
        if (carHeight > controllerMaxHeight)
        {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;
        }
        else if (carHeight > passengerCarMaxHeight)             //если высота тр.ср. больше максимальной высоты пассажирского то это
        {                                                       //значит что во всём методе находящемся в {} определяется стоимость грузового тр.ср.
            double weight = car.weight; //создание переменной
            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight)                 //если вес больше то определяется стоимость грузового тр.ср.
            {
                price = cargoCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }
            else {
                price = cargoCarPrice;
            }
        } else if(carHeight < passengerCarMaxHeight) {          //окончание метода определяющего стоимость грузового тр.ср в том числе и по высоте
            double weight = car.weight;                         //Высота меньше чем максимальная высота легкового авто.   Получение веса тр.ср.
            if(weight > passengerCarMaxWeight) {                //если тяжелее легкового тр.ср., то оценивается как грузовое тр.ср
                price = cargoCarPrice;                                         //грузовой автомобиль
            } else {                                            //иначе определяется стоимость легкового тр.ср.
                price = passengerCarPrice;                                     //легковой автомобиль
            }
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}
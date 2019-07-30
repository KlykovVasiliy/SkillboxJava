import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanHelp {
    public static void main(String[] args) throws IOException {
        int numContainer = 0;
        int numBox = 0;
        int numTrucks = 0;

        int containerInTrucks = 12;
        int boxInContainers = 27;
        int boxInTrucks = containerInTrucks * boxInContainers;  //максимальное количество коробок в грузовике

        //Ввод значения с клавиатуры
        System.out.println("Введите пожалуйста количество коробок гуманитарной помощи: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int box = Integer.parseInt(reader.readLine());

        //Определение количество грузовиков и контейнеров для гуманитарной помощи
        int trucks = (int) Math.ceil((float)box / boxInTrucks);
        int container = (int) Math.ceil((float)box / boxInContainers);
        System.out.println("Для перевозки нужно: " + trucks + " грузовика и " + container + " контейнеров");

        //Распределение ящиков и контейнеров по грузовикам
        for(int a = 0; a < trucks; a++) {
            if(numTrucks < trucks) {
                numTrucks++;
                System.out.println("Грузовик " + numTrucks + " ====================");
                for(int b = 0; b < containerInTrucks; b++) {
                    if(numContainer < container) {
                        numContainer++;
                        System.out.println("Контейнер " + numContainer + " ========== ");
                        for(int c = 0; c < boxInContainers; c++) {
                            if(numBox < box) {
                                numBox++;
                                System.out.println("Ящик " + numBox);
                            } else if(numBox >= box) {
                                System.out.println("Погрузка завершена " + numBox);
                                break;
                            }
                        }
                    } else break;
                }

            } else break;
        }

    }
}

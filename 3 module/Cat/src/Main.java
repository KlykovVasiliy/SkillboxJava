
public class Main
{
    public static void main(String[] args)
    {
        Cat tom = new Cat();
        Cat jerry = new Cat();
        Cat flash = new Cat();
        Cat batman = new Cat();
        Cat solomon = new Cat();


        String catSonya = "Sleeping";
        String catDead = "Dead";
        String catBoom = "Exploded";

        //Создание кота с весом введенным вручную
        Cat lopuh = new Cat(4698.64);    //живой
        lopuh.coatColor(ColorCats.READHEAD);
        System.out.println("Весс кота lopuh " + lopuh.getWeight());
        lopuh.getColor();
        lopuh.drink(lopuh.getWeight() / 100);
        lopuh.feed(lopuh.getWeight() / 50);
        lopuh.catTualet();
        lopuh.getFootEaten();
        System.out.println("=========================================================================================");

        //Cat tom покушал и попил воды
        tom.coatColor(ColorCats.WHITE);
        System.out.println("Весс кота tom: " + tom.getWeight());
        tom.getColor();
        tom.feed(2000.00);
        tom.meow();
        tom.drink(tom.getWeight() / 100);
        System.out.println("Весс кота tom после кормления: " + tom.getWeight());
        System.out.println("Состояние кота tom после кормления: " + tom.getStatus());
        tom.catTualet();
        tom.getFootEaten();
        System.out.println("=========================================================================================");


        //Cat jerry переел и взорвался
        jerry.coatColor(ColorCats.WHITE_BLACK);
        System.out.println("Весс кота jerry: " + jerry.getWeight());
        jerry.getColor();
        jerry.feed(900.00);
        jerry.drink(jerry.getWeight() / 100);
        System.out.println("Весс кота jerry после кормления: " + jerry.getWeight());
        for (int i = 0; i <= 100; i++) {
            if(jerry.getStatus().equals(catSonya)) {
                jerry.feed(70.00);
                jerry.drink(30.00);
                jerry.meow();
                System.out.println("Весс кота jerry: " + jerry.getWeight());
            } else if(jerry.getStatus().equals(catBoom)) {
                System.out.println("jerry переел и " + jerry.getStatus() + ". весс jerry " + jerry.getWeight());
                break;
            } else if(jerry.getStatus().equals(catDead)) {
                System.out.println("jerry домяукался и " + jerry.getStatus() + ". весс jerry " + jerry.getWeight());
                break;
            } else {
                System.out.println("jerry играет");
            }
        }
        jerry.getFootEaten();
        System.out.println("=========================================================================================");


        //Cat flash домяукался
        flash.coatColor(ColorCats.WHITE_BLACK_REDHEAD);
        System.out.println("Весс кота flash: " + flash.getWeight());
        flash.getColor();
        flash.feed(1507.00);
        flash.drink(flash.getWeight() / 100);
        System.out.println("Весс кота flash после кормления: " + flash.getWeight());
        int a = 0;
        do {
            a++;
            flash.meow();
            System.out.println("Весс кота flash: " + flash.getWeight());
            if (flash.getStatus().equals(catDead)) {
                System.out.println("flash домяукался и умер, " + "весс кота flash " + flash.getWeight());
                break;
            }
        } while (a < 8000);
        flash.getFootEaten();
        System.out.println("=========================================================================================");


        //Cat batman покушал и мяукал до состояние играет
        batman.coatColor(ColorCats.BLACK);
        System.out.println("Весс кота batman: " + batman.getWeight());
        batman.getColor();
        batman.feed(3000.00);
        System.out.println("Весс кота batman после кормления: " + batman.getWeight());
        for (int b = 0; b < 4000; b++) {
            if(batman.getStatus().equals(catSonya)) {
                batman.meow();
                System.out.println("Весс кота batman: " + batman.getWeight());
            } else if(batman.getStatus().equals(catDead)) {
                System.out.println("Кот batman до мяукался. Состояние кота batman после meow: " + batman.getStatus());
                break;
            } else if(batman.getStatus().equals(catBoom)) {
                System.out.println("Кот batman переел. Состояние кота batman после meow: " + batman.getStatus());
                break;
            } else {
                System.out.println("batman играет");
            }
        }
        batman.getFootEaten();
        System.out.println("=========================================================================================");


        //Кот poloskun клон кота solomon
        Cat poloskun = Cat.copy(solomon);
        System.out.println("Кот poloskun клон кота solomon весит: " + poloskun.getWeight());
        System.out.println("Кот poloskun клон кота solomon в статусе: " + poloskun.getStatus());
        poloskun.feed(2437.61);
        poloskun.getFootEaten();
        System.out.println("Кот poloskun клон кота solomon весит: " + poloskun.getWeight());
        System.out.println("=========================================================================================");

        //Кот solomon и клон захотели покушать
        solomon.meow();
        solomon.drink(134.58);
        solomon.feed(417.94);
        System.out.println("Весс кота solomon после совместного кормления: " + solomon.getWeight());
        solomon.getFootEaten();

        poloskun.meow();
        poloskun.setColorHair("Дымчатый цвет");
        System.out.println(poloskun.getColorHair());
        poloskun.drink(50.00);
        poloskun.feed(400.37);
        System.out.println("Весс клона после совместного кормления: " + poloskun.getWeight());
        poloskun.getFootEaten();
        System.out.println("=========================================================================================");

        //Cat solomon
        solomon.coatColor(ColorCats.GRAY);
        System.out.println("Весс кота solomon: " + solomon.getWeight());
        solomon.getColor();
        for (int c = 0; c <= 20; c++ ) {
            solomon.meow();
        }
        solomon.feed(1300.00);
        solomon.drink(solomon.getWeight() / 100);
        System.out.println("Состояние кота solomon: " + solomon.getStatus());
        System.out.println("Весс кота solomon после кормления: " + solomon.getWeight());
        solomon.getFootEaten();
        System.out.println("=========================================================================================");


        //Счетчик котов
        Cat.getCount();

    }
}
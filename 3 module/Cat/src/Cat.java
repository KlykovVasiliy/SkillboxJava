

public class Cat
{
    private ColorCats color;

    private static int count = 0;

    private String colorHair;
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;
    private double feedCat = 0.00;
    private boolean catAlive = true;

    public static final int EYES = 2;
    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;



    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
    }

    public Cat(double weight) {
        this();
        this.weight = weight;
    }

    //конструктор для создания копии
    public static Cat copy(Cat original) {
        Cat copy = new Cat();
        copy.originWeight = original.originWeight;
        copy.weight = original.weight;
        copy.feedCat = original.feedCat;
        return copy;
    }

    public void setColorHair (String colorHair) {
        this.colorHair = colorHair;
    }
    public String getColorHair() {
        return colorHair;
    }

    public void coatColor(ColorCats colorCats) {
        color = colorCats;
    }


    public void getColor() {
        System.out.println(color);
    }

    public boolean aliveOrDead() {
        if(getStatus().equals("Sleeping") || getStatus().equals("Playing")) {
            catAlive = true;
        } else if(getStatus().equals("Dead") || getStatus().equals("Exploded")) {
            catAlive = false;
        }
        return catAlive;
    }

    public void catDead() {
        count--;
        System.out.println("Котик уже умер. ");
    }

    public void meow() {
        if(aliveOrDead()) {
            weight = weight - 1;
            System.out.println("Meow");
//            if(!aliveOrDead()) catDead();
        } else if(!aliveOrDead()) catDead();
    }

    public void catTualet() {
        if(aliveOrDead()) {
            weight = weight - 100;
            System.out.println("prprprprprprppr ");
//            if(!aliveOrDead()) catDead();
        } else if(!aliveOrDead()) catDead();
    }

    public void feed(Double amount) {
        if(aliveOrDead()) {
            weight = weight + amount;
            feedCat = feedCat + amount;
//            if(!aliveOrDead()) catDead();
        } if(!aliveOrDead()) catDead();
    }

    public double getFootEaten() {
        System.out.println("Масса съеденной котом еды: " + feedCat);
        return feedCat;
    }

    public static int getCount() {
        System.out.println("Количество живых котов: " + count);
        return count;
    }

    public void drink(Double amount) {
        if(aliveOrDead()) {
            weight = weight + amount;
        } else if(!aliveOrDead()) catDead();

    }

    public double getWeight() {
        return weight;
    }

    public String getStatus() {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}
package core;

public class Car
{
    public String number;                                                      //создание переменной
    public int height;                                                         //создание переменной
    public double weight;                                                      //создание переменной
    public boolean hasVehicle;                                                 //создание переменной
    public boolean isSpecial;                                                  //создание переменной


    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }


    public void setHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }


    public void setHasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    }
    public boolean getHasVehicle() {
        return hasVehicle;
    }


    public void setSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }
    public boolean getIsSpecial() {
        return isSpecial;
    }

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}
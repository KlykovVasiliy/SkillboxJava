public class Main {
    public static void main(String[] args) {
        int vasyaAge = 27;
        int linaAge = 23;
        int natashaAge = 25;

        int min = 0;
        int middle = 0;
        int max = 0;

        if(vasyaAge >= linaAge) {
            if(natashaAge <= linaAge){
                min = natashaAge;
                middle = linaAge;
                max = vasyaAge;
            } else if(natashaAge >= vasyaAge) {
                min = linaAge;
                middle = vasyaAge;
                max = natashaAge;
            } else {
                min = linaAge;
                middle = natashaAge;
                max = vasyaAge;
            }
        } if(vasyaAge <= linaAge) {
            if(natashaAge <= vasyaAge) {
                min = natashaAge;
                middle = vasyaAge;
                max = linaAge;
            } else if(natashaAge >= linaAge) {
                min = vasyaAge;
                middle = linaAge;
                max = natashaAge;
            } else {
                min = vasyaAge;
                middle = natashaAge;
                max = linaAge;
            }
        }
        System.out.println("Возраст самого младшего: " + min);
        System.out.println("Возраст среднего: " + middle);
        System.out.println("Возраст самого старшего: " + max);
    }
}

import clients.Client;
import clients.IndividualClient;
import clients.LegalEntity;

public class Main {
    public static void main(String[] args) {
        Client fizik = new IndividualClient();
        Client yurik = new LegalEntity();
        Client indivPredpri = new IndividualClient();

        fizik.balance();
        fizik.replenish(10);
        fizik.replenish(-5);
        fizik.withdrawal(20);
        System.out.println("=============================================================");

        yurik.balance();
        yurik.replenish(50);
        yurik.replenish(-20);
        yurik.withdrawal(100);
        yurik.withdrawal(30);
        System.out.println("=============================================================");

        indivPredpri.balance();
        indivPredpri.replenish(100);
        indivPredpri.replenish(-30);
        indivPredpri.withdrawal(20);
        indivPredpri.withdrawal(200);
        indivPredpri.withdrawal(-10);
        System.out.println("=============================================================");
    }
}

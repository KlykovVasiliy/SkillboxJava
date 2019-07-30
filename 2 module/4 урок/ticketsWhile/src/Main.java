public class Main {
    public static void main(String[] args) {
        int tickets = 199999;
        int endTickets = 235000;
        while (tickets < endTickets) {
            tickets++;
            if(tickets > 210000 && tickets < 220000) {
                continue;
            }
            System.out.println("Tickets № " + tickets);

        }
    }
}

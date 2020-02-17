import com.skillbox.airport.*;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println("В аэропорту присутствует " + airport.getTerminals().size() + " терминала(ов)");

        for(int i = 0; i < airport.getTerminals().size(); i++) {
            System.out.println("В терминале " + airport.getTerminals().get(i) + " припарковано " +
                    airport.getTerminals().get(i).getParkedAircrafts().size() + " самолета(ов)");
            System.out.println("В рейсах находятся " + airport.getTerminals().get(i).getFlights().size());
            System.out.println("-------------------------------------------------------------------------------------");

            for(int j = 0; j < airport.getTerminals().get(i).getParkedAircrafts().size(); j++) {
                System.out.println(airport.getTerminals().get(i).getParkedAircrafts().get(j) + " на стоянке в терминале");
            }
            System.out.println("=====================================================================================");

        }
        System.out.println("Всего самолетов в аэропорту " + airport.getAllAircrafts().size());
    }
}

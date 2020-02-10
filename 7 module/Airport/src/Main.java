import com.skillbox.airport.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private static final Calendar timeNow = Calendar.getInstance();

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println("В аэропорту присутствует " + airport.getTerminals().size() +
                " терминала(ов)");

        for (int i = 0; i < airport.getTerminals().size(); i++) {
            System.out.println("В терминале " + airport.getTerminals().get(i).getName() +
                    " припарковано " + airport.getTerminals().get(i).getParkedAircrafts().size() +
                    " самолета(ов)");
            System.out.println("В рейсах находятся " +
                    airport.getTerminals().get(i).getFlights().size());
            System.out.println("-----------------------------------------------------------------");

            for (int j = 0; j < airport.getTerminals().get(i).getParkedAircrafts().size(); j++) {
                System.out.println(airport.getTerminals().get(i).getParkedAircrafts().get(j) +
                        " на стоянке в терминале");
            }
            System.out.println("=================================================================");
        }

        System.out.println("Всего самолетов в аэропорту " + airport.getAllAircrafts().size());

        listingAircraftForTheNextTwoHours(airport);
    }

    public static void listingAircraftForTheNextTwoHours(Airport airport) {
        timeDisplayNow(timeNow.getTime());
        Stream.of(airport)
                .flatMap(t -> t.getTerminals().stream())
                .flatMap(fl -> fl.getFlights().stream())
                .filter(fl2 -> isLessThanTwoHours(fl2.getDate()))
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .sorted(Comparator.comparing(Flight::getDate))
                .forEach(t -> System.out.println(timeFormat.format(t.getDate()) + " = " +
                        t.getAircraft().getModel()));
    }

    public static boolean isLessThanTwoHours(Date dateDeparture) {
        Calendar twoHoursLater = (Calendar) timeNow.clone();
        twoHoursLater.add(Calendar.HOUR, 2);
        return dateDeparture.before(twoHoursLater.getTime())
                && dateDeparture.after(timeNow.getTime());
    }

    public static void timeDisplayNow(Date time) {
        System.out.printf("%nТекущее время: " + time + "%n");
    }

}
/**
 * создать новый список
 * добавить в список время вылета flight.getDate() и getAircraft().getModel()
 * произвести сортировку по времени вылета
 */

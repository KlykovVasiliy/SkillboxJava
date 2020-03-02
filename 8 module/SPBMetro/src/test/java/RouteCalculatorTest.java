import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    /**
     * Схема миниметро для тестирования
     * st6                       st10
     * --------*----------*------------*---------------*--------*--- (1 line)
     * st1    st2 /            st3           st4|       st5
     * /                               |
     * * st7                           |
     * /                               * st11
     * * st8                           |
     * /                               |
     * / st9                           | st12
     * ---------*-----------------*-------------*---------- (4 line)
     * (2 line)  st13         st14         st15 (3 line)
     */

    private RouteCalculator routeCalculator;
    private StationIndex stationIndex;
    private List<Station> intersection1;
    private List<Station> intersection2;
    private List<Station> intersection3;
    private List<Station> intersection4;

    private Line line1 = new Line(1, "Первая");
    private Line line2 = new Line(2, "Вторая");
    private Line line3 = new Line(3, "Третья");
    private Line line4 = new Line(4, "Четвертая");

    Station st1 = new Station("A", line1);
    Station st2 = new Station("B", line1);                                  //Пересадка на вторую линию(st6)
    Station st3 = new Station("C", line1);
    Station st4 = new Station("D", line1);                                       //Пересадка на третью линию(st10)
    Station st5 = new Station("E", line1);

    Station st6 = new Station("F", line2);                                     //Пересадка на первую линию(st2)
    Station st7 = new Station("G", line2);
    Station st8 = new Station("H", line2);
    Station st9 = new Station("I", line2);                                      //Пересадка на четвертую линию

    Station st10 = new Station("J", line3);                                     //Пересадка на первую линию(st4)
    Station st11 = new Station("K", line3);
    Station st12 = new Station("L", line3);                                   //Пересадка на четвертую линию

    Station st13 = new Station("M", line4);                                        //Пересадка на вторую линию
    Station st14 = new Station("O", line4);
    Station st15 = new Station("P", line4);                                       //Пересадка на третью линию

    private static double TRAVEL_TIME_ON_LINE = 2.5;
    private static double TIME_TRANSFER_ON_ANOTHER_LINE = 3.5;


    @Override
    @Before
    protected void setUp() {
        stationIndex = new StationIndex();

        intersection1 = new ArrayList<>();
        intersection2 = new ArrayList<>();
        intersection3 = new ArrayList<>();
        intersection4 = new ArrayList<>();

        addLines(line1, line2, line3, line4);
        addStationOnTheLine();
        addStationsInStationIndex(st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, st12, st13, st14, st15);
        addInterSections();
        addInterSectionsInStationIndex();

        routeCalculator = new RouteCalculator(stationIndex);
    }

    @Test
    public void testCalculateDurationOnOneTheLine() {
        double expected = RouteCalculator.calculateDuration(getRoute(st1, st5));
        double actual = TRAVEL_TIME_ON_LINE * 4;
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateDurationOnTwoTheLines() {
        double expected = RouteCalculator.calculateDuration(getRoute(st1, st8));
        double actual = TRAVEL_TIME_ON_LINE * 3 + TIME_TRANSFER_ON_ANOTHER_LINE;
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateDurationOnThreeTheLines() {
        double expected = RouteCalculator.calculateDuration(getRoute(st3, st14));
        double actual = TRAVEL_TIME_ON_LINE * 4 + TIME_TRANSFER_ON_ANOTHER_LINE * 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testShortestRouteOnOneTheLine() {
        List<Station> expected = routeCalculator.getShortestRoute(st1, st5);
        List<Station> actual = makeRoute("A", "B", "C", "D", "E");
        assertEquals(expected, actual);
    }

    @Test
    public void testShortestRouteOnTwoLines() {
        List<Station> expected = routeCalculator.getShortestRoute(st1, st8);
        List<Station> actual = makeRoute("A", "B", "F", "G", "H");
        assertEquals(expected, actual);
    }

    @Test
    public void testShortestRouteOnThreeLines() {
        List<Station> expected = routeCalculator.getShortestRoute(st3, st14);
        List<Station> actual = makeRoute("C", "D", "J", "K", "L", "P", "O");
        assertEquals(expected, actual);
    }

    private List<Station> makeRoute(String... nameStations) {
        List<Station> stationList = new ArrayList<>();
        for (String nameStation : nameStations) {
            stationList.add(stationIndex.getStation(nameStation));
        }
        return stationList;
    }

    private void addLines(Line... lines) {
        for (Line line : lines) {
            stationIndex.addLine(line);
        }
    }

    private void addStationOnTheLine() {
        line1.addStation(st1);
        line1.addStation(st2);
        line1.addStation(st3);
        line1.addStation(st4);
        line1.addStation(st5);
        line2.addStation(st6);
        line2.addStation(st7);
        line2.addStation(st8);
        line2.addStation(st9);
        line3.addStation(st10);
        line3.addStation(st11);
        line3.addStation(st12);
        line4.addStation(st13);
        line4.addStation(st14);
        line4.addStation(st15);
    }

    private void addStationsInStationIndex(Station... stations) {
        for (Station station : stations) {
            stationIndex.addStation(station);
        }
    }

    private void addInterSections() {
        intersection1.add(st2);
        intersection1.add(st6);
        intersection2.add(st4);
        intersection2.add(st10);
        intersection3.add(st9);
        intersection3.add(st13);
        intersection4.add(st12);
        intersection4.add(st15);
    }

    private void addInterSectionsInStationIndex() {
        stationIndex.addConnection(intersection1);
        stationIndex.addConnection(intersection2);
        stationIndex.addConnection(intersection3);
        stationIndex.addConnection(intersection4);
    }

    private List<Station> getRoute(Station from, Station to) {
        return routeCalculator.getShortestRoute(from, to);
    }
}
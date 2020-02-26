import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    /**
     *          Схема миниметро для тестирования
     *                     st6                       st10
     * --------*----------*------------*---------------*--------*--- (1 line)
     *        st1    st2 /            st3           st4|       st5
     *                /                               |
     *               * st7                           |
     *              /                               * st11
     *             * st8                           |
     *            /                               |
     *           / st9                           | st12
     * ---------*-----------------*-------------*---------- (4 line)
         (2 line)  st13         st14         st15 (3 line)
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

    Station st1 = new Station("Айвазовская", line1);
    Station st2 = new Station("Ставропольская", line1);                                  //Пересадка на вторую линию(st6)
    Station st3 = new Station("Северная", line1);
    Station st4 = new Station("Селезнева", line1);                                       //Пересадка на третью линию(st10)
    Station st5 = new Station("Суворова", line1);

    Station st6 = new Station("Карасунская", line2);                                     //Пересадка на первую линию(st2)
    Station st7 = new Station("Седина", line2);
    Station st8 = new Station("Старокубанская", line2);
    Station st9 = new Station("Российская", line2);                                      //Пересадка на четвертую линию

    Station st10 = new Station("Московская", line3);                                     //Пересадка на первую линию(st4)
    Station st11 = new Station("Зиповская", line3);
    Station st12 = new Station("Дзержинского", line3);                                   //Пересадка на четвертую линию

    Station st13 = new Station("Красная", line4);                                        //Пересадка на вторую линию
    Station st14 = new Station("Гаврилова", line4);
    Station st15 = new Station("Одесская", line4);                                       //Пересадка на третью линию



    @Override
    @Before
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();

        intersection1 = new ArrayList<>();
        intersection2 = new ArrayList<>();
        intersection3 = new ArrayList<>();
        intersection4 = new ArrayList<>();

        addLines();
        addStationOnTheLine();
        addStationsInStationIndex();
        addInterSections();
        addInterSectionsInStationIndex();

        routeCalculator = new RouteCalculator(stationIndex);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        stationIndex = null;
        intersection1 = null;
        intersection2 = null;
        intersection3 = null;
        intersection4 = null;
        routeCalculator = null;
    }

    @Test
    public void testCalculateDurationOnOneTheLine() {
        double actual = RouteCalculator.calculateDuration(getRoute(st1, st5));
        double expected = 10;
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateDurationOnTwoTheLines() {
        double actual = RouteCalculator.calculateDuration(getRoute(st1, st8));
        double expected = 11;
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateDurationOnThreeTheLines() {
        double actual = RouteCalculator.calculateDuration(getRoute(st3, st14));
        double expected = 17;
        assertEquals(expected, actual);
    }

    @Test
    public void testShortestRouteOnOneTheLine() {
        List<Station> actual = routeCalculator.getShortestRoute(st1, st5);
        List<Station> expected = createRouteSheetOnOneTheLine();
        assertEquals(expected, actual);
    }

    @Test
    public void testShortestRouteOnTwoLines() {
        List<Station> actual = routeCalculator.getShortestRoute(st1, st8);
        List<Station> expected = createRouteSheetOnTwoTheLines();
        assertEquals(expected, actual);
    }

    @Test
    public void testShortestRouteOnThreeLines() {
        List<Station> actual = routeCalculator.getShortestRoute(st3, st14);
        List<Station> expected = createRouteSheetOnThreeTheLines();
        assertEquals(expected, actual);
    }

    private List<Station> createRouteSheetOnOneTheLine() {
        List<Station> list = new ArrayList<>();
        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);
        list.add(st5);
        return list;
    }

    private List<Station> createRouteSheetOnTwoTheLines () {
        List<Station> list = new ArrayList<>();
        list.add(st1);
        list.add(st2);
        list.add(st6);
        list.add(st7);
        list.add(st8);
        return list;
    }

    private List<Station> createRouteSheetOnThreeTheLines () {
        List<Station> list = new ArrayList<>();
        list.add(st3);
        list.add(st4);
        list.add(st10);
        list.add(st11);
        list.add(st12);
        list.add(st15);
        list.add(st14);
        return list;
    }

    private void addLines() {
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addLine(line4);
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

    private void addStationsInStationIndex() {
        stationIndex.addStation(st1);
        stationIndex.addStation(st2);
        stationIndex.addStation(st3);
        stationIndex.addStation(st4);
        stationIndex.addStation(st5);
        stationIndex.addStation(st6);
        stationIndex.addStation(st7);
        stationIndex.addStation(st8);
        stationIndex.addStation(st9);
        stationIndex.addStation(st10);
        stationIndex.addStation(st11);
        stationIndex.addStation(st12);
        stationIndex.addStation(st13);
        stationIndex.addStation(st14);
        stationIndex.addStation(st15);
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
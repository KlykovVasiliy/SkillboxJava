import lombok.Getter;
import tableRowParameters.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LineHandlerHTML {
    private static final Comparator<Station> comparator = Comparator
            .comparing(Station::getLineNumber);

    private static final Map<String, String> mapColors = new HashMap<>();
    @Getter
    private final Map<String, List<String>> stations;       //<String lineNumber, List<Station>
    @Getter
    private final List<Line> lines;
    @Getter
    private final Set<List<Station>> connections;

    public LineHandlerHTML(String url) {
        stations = new HashMap<>();
        lines = new ArrayList<>();
        connections = new HashSet<>();
        setALink(url);
    }

    private void setALink(String url) {           //получение данных с сайта wiki
        mapColors.put("#EF161E", "Red");
        mapColors.put("#2DBE2C", "Green");
        mapColors.put("#0078BE", "Blue");
        mapColors.put("#00BFFF", "Deep Sky Blue");
        mapColors.put("#8D5B2D", "Brown");
        mapColors.put("#ED9121", "Orange");
        mapColors.put("#800080", "Violet");
        mapColors.put("#FFD702", "Yellow");
        mapColors.put("#999999", "Gray");
        mapColors.put("#99CC00", "Light Green");
        mapColors.put("#82C0C0", "Turquoise");
        mapColors.put("#A1B3D4", "Gray Blue");
        mapColors.put("#DE64A1", "Pink");
        mapColors.put("#9999FF", "Sky");
        mapColors.put("#FFFFFF", "White");
        selectRowsToProcess(url);
    }

    private void selectRowsToProcess(String pathFile) {                  //получение таблиц для обработки
        File fileHtml = new File(pathFile);
        Document doc = null;
        try {
            doc = Jsoup.parse(fileHtml, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByClass("standard sortable");
        elements.stream()
                .map(tables -> tables.getElementsByTag("tr"))
                .flatMap(table -> table.stream()                                  //поток проходит по строкам таблицы
                        .skip(1)
                        .map(strTable -> strTable.getElementsByTag("tr")))
                .forEach(s -> splitARowIntoColumns(s.select("tr")));
    }

    private void splitARowIntoColumns(Elements tableRow) {
        List<Elements> columnList = tableRow.stream()
                .map(td -> td.getElementsByTag("td"))
                .flatMap(t -> t.stream()
                        .map(tt -> tt.getElementsByTag("td")))
                .collect(Collectors.toList());
        selectStationLineIntersection(columnList);
    }

    private void selectStationLineIntersection(List<Elements> tableRow) {    //переименовать метод
        List<Line> linesList = new ArrayList<>();
        List<Station> stationList = new ArrayList<>();
        List<Station> intersectionStations = null;

        for (int i = 0; i < tableRow.size(); i++) {
            Elements currentColumn = tableRow.get(i);
            if (i == 0) {
                linesList = getListLines(getLineNumber(currentColumn),
                        getLineName(currentColumn), getLineColor(currentColumn));
            } else if (i == 1) {
                stationList = getStation(linesList, getNameStation(currentColumn));
            } else if (i == 3) {
                intersectionStations = getListStationIntersection(getNumberLineIntersection(currentColumn),
                        getNameStationIntersection(currentColumn));
            }
        }
        if (intersectionStations != null && intersectionStations.size() > 0) {
            intersectionStations.addAll(stationList);
            intersectionStations.sort(comparator);
        }
        addDataForSave(linesList, stationList, intersectionStations);
    }

    private List<String> getLineNumber(Elements tableRowCell) {
        List<String> listNumbersLines = tableRowCell.stream()
                .map(x -> x.getElementsByClass("sortkey"))
                .flatMap(c -> c.stream()
                        .map(v -> v.getElementsByClass("sortkey").text()))
                .collect(Collectors.toList());
        listNumbersLines.remove(listNumbersLines.size() - 1);
        return listNumbersLines.stream()
                .map(LineHandlerHTML::removingZeroAtTheBeginning)
                .collect(Collectors.toList());
    }

    private static String removingZeroAtTheBeginning(String numberLines) {
        Pattern pattern = Pattern.compile("^0[\\d]{1,2}[AА]?$");
        Matcher matcher = pattern.matcher(numberLines);
        if (matcher.find()) {
            numberLines = numberLines.substring(1);
        }
        return numberLines;
    }

    private List<String> getLineName(Elements tableRowCell) {
        return tableRowCell.stream()
                .map(x -> x.getElementsByTag("span"))
                .flatMap(s -> s.stream()
                        .map(v -> v.getElementsByAttribute("alt"))
                        .filter(b -> (!b.isEmpty()))
                        .map(n -> n.attr("alt")))
                .collect(Collectors.toList());
    }

    private List<String> getLineColor(Elements tableRowCell) {
        List<String> listColorsLines = new ArrayList<>();
        String lineColor = tableRowCell.attr("style");
        String tempColor;
        if (lineColor.length() == 18) {
            tempColor = mapColors.get(lineColor.substring(lineColor.indexOf(":") + 1));
            listColorsLines.add(tempColor);
        } else if (lineColor.length() > 18) {
            tempColor = mapColors.get(lineColor.substring(lineColor.indexOf("from") + 5,
                    lineColor.indexOf("),")));
            listColorsLines.add(tempColor);
            tempColor = mapColors.get(lineColor.substring(lineColor.indexOf("to(") + 3,
                    lineColor.lastIndexOf("))")));
            listColorsLines.add(tempColor);
        } else {
            listColorsLines.add("tableRowParameters.Station Closed");
        }
        return listColorsLines;
    }

    private String getNameStation(Elements tableRowCell) {
        return tableRowCell.first().getElementsByTag("a").first().text();
    }

    private List<String> getNumberLineIntersection(Elements tableRowCell) {
        List<String> listNumberLines = tableRowCell.stream()
                .map(i -> i.getElementsByClass("sortkey"))
                .flatMap(a -> a.stream()
                        .map(s -> s.getElementsByClass("sortkey").text()))
                .collect(Collectors.toList());
        return listNumberLines.stream()
                .map(LineHandlerHTML::removingZeroAtTheBeginning)
                .collect(Collectors.toList());
    }

    private List<String> getNameStationIntersection(Elements tableRowCell) {
        return tableRowCell.stream()
                .map(m -> m.getElementsByAttribute("title"))
                .flatMap(n -> n.stream()
                        .map(b -> b.attr("href"))
                        .filter(a -> (!a.isEmpty())))
                .map(LineHandlerHTML::getNameStationIntersection)
                .map(LineHandlerHTML::trimNameStationIntersection)
                .collect(Collectors.toList());
    }

    private static String trimNameStationIntersection(String nameStation) {
        if (nameStation.isEmpty()) {
            nameStation = "";
        } else if (nameStation.contains("станция")) {
            nameStation = nameStation.substring(0, nameStation.indexOf("станция") - 1).trim();
        } else if (nameStation.contains("Википедия")) {
            nameStation = nameStation.substring(0, nameStation.indexOf("Википедия") - 2).trim();
        }
        return nameStation;
    }

    /*
        tableRowParameters.Line 7, station Волгоградский проспект, обнаружена ошибка на сайте при получении
         наименования станции с пересечения с line 14
     */
    private static String getNameStationIntersection(String urlStationMetro) {
        String wiki = "https://ru.wikipedia.org" + urlStationMetro;
        Document doc;
        String nameStationIntersection;
        try {
            doc = Jsoup.connect(wiki).maxBodySize(0).get();
            nameStationIntersection = doc.head().getElementsByTag("title").text();
        } catch (IOException e) {
            nameStationIntersection = "Неизвестная станция";
            e.printStackTrace();
        }
        return nameStationIntersection;
    }

    private List<Line> getListLines(List<String> linesNumbers, List<String> linesNames,
                                    List<String> linesColors) {
        List<Line> listLines = new ArrayList<>();
        for (int i = 0; i < linesNumbers.size(); i++) {
            listLines.add(new Line(linesNumbers.get(i), linesNames.get(i), linesColors.get(i)));
        }
        return listLines;
    }

    private List<Station> getStation(List<Line> linesList, String stationName) {
        List<Station> stationList = new ArrayList<>();
        for (Line line : linesList) {
            stationList.add(new Station(line.getLineNumber(), stationName));
        }
        return stationList;
    }

    private List<Station> getListStationIntersection(List<String> listNumberLine,
                                                     List<String> listNameStation) {
        List<Station> listStation = new ArrayList<>();
        for (int i = 0; i < listNumberLine.size(); i++) {
            listStation.add(new Station(listNumberLine.get(i), listNameStation.get(i)));
        }
        return listStation;
    }

    private void addDataForSave(List<Line> linesList, List<Station> stationList,
                                List<Station> intersectionStations) {
        for (int i = 0; i < linesList.size(); i++) {
            Line line = new Line(linesList.get(i).getLineNumber(), linesList.get(i).getLineName(),
                    linesList.get(i).getLineColor());
            Station station = new Station(stationList.get(i).getLineNumber(),
                    stationList.get(i).getStationName());
            if (linesList.size() == 0) {
                continue;
            }
            addStation(station);
            addLine(line);
            if (intersectionStations.size() > 1) {
                connections.add(intersectionStations);
            }
        }
    }

    private void addStation(Station station) {
        String lineNumber = station.getLineNumber();
        if (!stations.containsKey(lineNumber)) {
            stations.put(lineNumber, new ArrayList<>());
        }
        stations.get(lineNumber).add(station.getStationName());
    }

    private void addLine(Line line) {
        if (!lines.contains(line)) {
            lines.add(line);
        }
    }
}
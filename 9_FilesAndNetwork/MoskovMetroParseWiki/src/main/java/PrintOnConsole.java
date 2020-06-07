import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tableRowParameters.Station;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class PrintOnConsole {
    private final Map<String, List<Station>> stationMapByNumbers = new HashMap<>();

    public void printCountStationByLines(Path pathFile) {
        parseJsonFile(pathFile);
        printInConsole();
    }

    private void parseJsonFile(Path pathFile) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(Files.readString(pathFile));

            JSONObject jsonObject = (JSONObject) jsonData.get("stations");
            parseStations(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject -> {
            String lineNumber = (String) lineNumberObject;
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject -> {
                Station station = new Station(lineNumber, (String) stationObject);
                addToMap(station);
            });
        });
    }

    private void addToMap(Station station) {
        String numberLine = station.getLineNumber();
        String nameStation = station.getStationName();
        if (!stationMapByNumbers.containsKey(numberLine)) {
            stationMapByNumbers.put(numberLine, new ArrayList<>());
        }
        stationMapByNumbers.get(numberLine).add(new Station(numberLine, nameStation));
    }

    private void printInConsole() {
        for (Map.Entry<String, List<Station>> pair : stationMapByNumbers.entrySet()) {
            String key = pair.getKey();
            int countStation = pair.getValue().size();
            System.out.printf("На линии № %s находится %d станций.%n", key, countStation);
        }
    }
}
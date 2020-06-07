package tableRowParameters;

import lombok.Getter;

import java.util.Objects;

public class Station {
    @Getter
    String lineNumber;

    @Getter
    String stationName;

    public Station(String lineNumber, String stationName) {
        this.lineNumber = lineNumber;
        this.stationName = stationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;
        return lineNumber.equals(station.lineNumber) &&
                stationName.equals(station.stationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, stationName);
    }

    @Override
    public String toString() {
        return "Station{" +
                "lineNumber='" + lineNumber + '\'' +
                ", stationName='" + stationName + '\'' + '}';
    }
}

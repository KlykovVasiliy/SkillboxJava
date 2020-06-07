package tableRowParameters;

import lombok.Getter;

public class Line {
    @Getter
    String lineNumber;
    @Getter
    String lineName;
    @Getter
    String lineColor;

    public Line(String lineNumber, String lineName, String lineColor) {
        this.lineNumber = lineNumber;
        this.lineName = lineName;
        this.lineColor = lineColor;
    }

    @Override                                                                         //есть сомнения верно ли написал
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Line line = (Line)obj;
        return this.getLineNumber() != null && this.getLineNumber().equals(line.getLineNumber());
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineNumber='" + lineNumber + '\'' +
                ", lineName='" + lineName + '\'' +
                ", lineColor='" + lineColor + '\'' + '}';
    }
}

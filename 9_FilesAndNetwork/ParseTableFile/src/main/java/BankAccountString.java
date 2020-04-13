import lombok.Getter;

import java.util.*;

public class BankAccountString {
    @Getter
    String operationDescription;
    @Getter
    double coming;
    @Getter
    double consumption;

    public BankAccountString(String operationDescription, double coming, double consumption) {
        this.operationDescription = operationDescription;
        this.coming = coming;
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        List<String> list = Arrays.asList(operationDescription, String.valueOf(coming),
                String.valueOf(consumption));
        return String.join("  -  ", list);
    }
}
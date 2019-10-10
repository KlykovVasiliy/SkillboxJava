import java.util.Comparator;

public class ComparatorSalary implements Comparator<AbstractEmployees> {

    @Override
    public int compare(AbstractEmployees o1, AbstractEmployees o2) {
        return Double.compare(o1.getMonthSalary(), o2.getMonthSalary());
    }
}


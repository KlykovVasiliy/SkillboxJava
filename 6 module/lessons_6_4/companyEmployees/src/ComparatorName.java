import java.util.Comparator;

public class ComparatorName implements Comparator<AbstractEmployees> {

    @Override
    public int compare(AbstractEmployees o1, AbstractEmployees o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
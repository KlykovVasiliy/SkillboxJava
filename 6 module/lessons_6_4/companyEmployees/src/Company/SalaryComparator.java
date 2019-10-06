package Company;
import java.util.Comparator;

public class SalaryComparator implements Comparator<AbstractEmployees> {

    @Override
    public int compare(AbstractEmployees o1, AbstractEmployees o2) {
        return Double.compare(o1.getMonthSalary(), o2.getMonthSalary());

//        if (o1.getMonthSalary() > o2.getMonthSalary()) {
//            return 1;
//        }
//        if (o1.getMonthSalary() < o2.getMonthSalary()) {
//            return -1;
//        }
//        return 0;
    }
}

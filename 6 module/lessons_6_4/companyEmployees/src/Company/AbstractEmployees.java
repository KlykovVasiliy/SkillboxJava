package Company;

public abstract class AbstractEmployees extends Company implements Employee {
    private String name;

    public AbstractEmployees(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}

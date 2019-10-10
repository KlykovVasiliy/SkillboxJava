public abstract class AbstractEmployees implements Employee {
    private String name;

    protected AbstractEmployees(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }
}
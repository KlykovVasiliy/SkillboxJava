public class Customer {
    private String name;
    private String phone;
    private String eMail;

    public Customer(String name, String eMail, String phone) {
        this.name = name;
        this.eMail = eMail;
        this.phone = phone;
    }

    public String toString() {
        return name + " - " + eMail + " - " + phone;
    }
}

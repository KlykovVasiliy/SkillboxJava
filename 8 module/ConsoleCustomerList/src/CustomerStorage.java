import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new RuntimeException("Wrong format. Correct format:\n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, getEmail(components[2]),
                getPhoneNumber(components[3])));
        outputMessageSuccessfullly();
    }

    public void listCustomers() {
        if (storage.isEmpty()) {
            throw new RuntimeException("The List Is Empty");
        } else {
            storage.values().forEach(System.out::println);
        }
    }

    public void removeCustomer(String name) {
        String[] familyAndName = name.split("\\s+");
        if (familyAndName.length != 2) {
            throw new RuntimeException("Wrong format. Correct format: Name Family");
        }
        if (storage.containsKey(name)) {
            storage.remove(name);
            outputMessageSuccessfullly();
        } else {
            throw new RuntimeException("Not listed");
        }
    }

    public int getCount() {
        return storage.size();
    }

    private String getEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w\\.]{2,20}@[a-z]{2,7}\\.[a-z]{2,3}$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            return email;
        } else {
            throw new RuntimeException("Wrong format email. " +
                    "Correct format: vasily.petrov@gmail.com");
        }
    }

    private String getPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+\\d{11}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find()) {
            return phoneNumber;
        } else {
            throw new RuntimeException("Wrong format phoneNumber. " +
                    "Correct format: +79215637722");
        }
    }

    private void outputMessageSuccessfullly() {
        System.out.println("Successfully");
    }
}
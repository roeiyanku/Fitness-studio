package gym.customers;


import java.util.ArrayList;
import java.util.Collections;

public class customers {
    private ArrayList<String> customers = new ArrayList<>();
    private static Customers instance;

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers();
        }
        return instance;
}
    public ArrayList<Client> getCustomers() {
        return new ArrayList<>(customers);
    }

    public void addCustomer(Client customer) {
        customers.add(customer);
    }
    public void removeCustomer(Client customer) {
        customers.remove(customer);
    }
}

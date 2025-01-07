package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * The Gym class represents a singleton instance of a gym.
 * It management gym data including clients, employees, sessions,
 * and the gym's financial balance.
 */


public class Gym {
    private static Gym instance;    // Singleton instance of the Gym class
    private String gymName;     // Name of the gym
    public static int gymBalance;    // Gym's financial balance
    private Secretary currentSecretary;     // Currently active secretary
    public static List<String> actionsHistory = new ArrayList<>(); // History of actions performed in the gym
    public static Map<Integer, Client> clients = new HashMap<>();  // Mapping of client IDs to their respective Client objects
    public static Map<Integer, Session> sessions = new HashMap<>();  // Mapping of session IDs to their respective Session objects
    public static Map<Integer, Person> employees = new HashMap<>(); // Mapping of employee IDs to their respective Person objects

    /**
     * Private constructor to enforce the singleton design pattern.
     * Initializes the gym with default values.
     */
    private Gym (){

        this.gymName = null;
        this.gymBalance = 0;
        this.currentSecretary = null;
    }
    /**
     * Provides the singleton instance of the Gym class.
     * If no instance exists, a new one is created.
     * @return the singleton instance of Gym
     */
    public static Gym getInstance(){
        if (instance == null){
            instance = new Gym();
        }
        return instance;
    }

    //Returns a map of all registered clients.
    public Map<Integer, Client> getClients() {
        return clients;
    }


    //Sets the name of the gym.
    public void setName(String name){
        this.gymName = name;
    }

    //Returns the currently active secretary.
    public Secretary getSecretary(){
        return currentSecretary;
    }

    /**
     * Sets a new secretary for the gym. If a secretary already exists, they are replaced.
     * Updates the employment status and the employee records.
     *
     * @param person the Person object representing the new secretary
     * @param salary the salary of the new secretary
     */
    public void setSecretary(Person person, int salary) {
        if (currentSecretary != null) {
            // Remove the current secretary from the employees list
            Gym.employees.remove(currentSecretary.getID(), currentSecretary);
            currentSecretary.isWorking = false;
            // Create a new Secretary and update records
            Secretary newSecretary = new Secretary(person, salary);
            Gym.employees.put(newSecretary.getID(), newSecretary);
            newSecretary.isWorking = true;// Update employment status
            actionsHistory.add("A new secretary has started working at the gym: " + newSecretary.getName());
        }
        else {
            // Assign the first secretary to the gym
            Secretary newSecretary = new Secretary(person, salary);
            Gym.employees.put(newSecretary.getID(), newSecretary);
            currentSecretary = newSecretary;
            currentSecretary.isWorking = true;
            actionsHistory.add("A new secretary has started working at the gym: " + newSecretary.getName());
        }
    }
    //Sets the gym's financial balance.
    public void setBalance(int balance){ gymBalance = balance;}

    //Retrieves the gym's current financial balance.
    public int getBalance() {return gymBalance;}





    //Provides a string representation of the gym's data
    @Override
    public String toString() {
        return
                "gym.management.Gym Name: " + gymName + "\n" +
                "gym.management.Gym gym.management.Secreary: " + (currentSecretary != null ? currentSecretary : "None") + "\n" +
                "gym.management.Gym Balance: " + getBalance() + "\n\n" +
                "Clients Data:\n" + formatMapValues(clients) + "\n\n" +
                "Employees Data:\n" + formatMapValues(employees) + "\n\n" +
                "Sessions Data:\n" + formatMapValues(sessions);
    }

    // General helper method to format only the values of a map
    private <K, V> String formatMapValues(Map<K, V> map) {
        if (map.isEmpty()) {
            return "None";
        }
        return map.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }


}

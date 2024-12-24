import java.util.ArrayList;

/**
 * Secretary class
 */



public class Secretary {

    private Person person;
    private static Secretary instance ; //Singleton use
    int salary;
    String role = "Secretary";
    int id;



    public static Secretary getInstance(){
        if (instance ==null){
            instance = new Secretary(person, salary);
        }
    }



    //setters and getters:

    public void setPerson(Person person) {
        this.person = person;
    }




    private void registerClient (Person person){



    }
    public ArrayList<Client> getClients() {
        return clients;
    }


   //for example: ID: 1113 | Name: Maayan | Gender: Female | Birthday: 21-12-2005 | Age: 19 | Balance: 50 | Role: Secretary | Salary per Month: 8000
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d | Role: %s | Salary per Month: d%",
                id, person.getName(), person.getGender(), person.getBirthday(), person.calculateAge(), person.getBalance(), role, salary);
    }







}
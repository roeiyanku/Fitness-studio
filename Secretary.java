/**
 * Secretary class
 */



public class Secretary {
    private Person person; //Delegation



    //constructor:
    public Secretary(String name, int money, Person.Gender gender, String birthday) {
        this.person = new Person(name, money, gender, birthday); //Delegation Initialized
    }







//צריך לוודא שיש רק מזכירה אחת


}
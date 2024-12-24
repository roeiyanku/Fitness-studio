/**
 *Person class
*/
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;


public class Person {

    private Gender gender;
    String birthday;
    String name;
    int balance;






//Constructor

    public Person(String name, int balance, Gender gender, String birthday){
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthday = birthday;


    }


    //Getters and Setters

    public String getName(){
        return name;
    }
    public int getBalance(){
        return balance;
    }
    public void setBalance(int balance){

        this.balance = balance;
    }
    public Gender getGender() {
        return gender;}

    public String getBirthday(){
    return birthday;
    }



    // Calculate the age of the person based on birthdate
    public int calculateAge() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birthDate = LocalDate.parse(this.birthday, formatter);
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate, currentDate).getYears();
    }
     
    // toString Method
      @Override
      public String toString() {
          return String.format("Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d",
                  name, gender.toString(), birthday, calculateAge(), balance);

      }
}
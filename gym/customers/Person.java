package gym.customers; /**
 *gym.customers.Person class
*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;


public class Person {

    private Gender gender;
    String birthday;
    String name;
    public int balance;
    int id;
    int counter = 0;






//Constructor

    public Person(String name, int balance, Gender gender, String birthday){
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthday = birthday;
        this.id = 1111 + counter;
        counter ++;


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

    public int getID(){
        return id;
    }

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
          return  "Name: " + name + " | gym.customers.Gender: " + gender + " | Birthday: " + birthday +
                  " | Age: " + calculateAge() + " | Balance: " + balance;
      }
}
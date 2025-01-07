package gym.customers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

// This class represents a person in the gym system
public class Person {
    private Gender gender;// The gender of the person
    String birthday; // The birthdate of the person
    String name;  // The name of the person
    public int balance;// The balance in the person's account
    int id;// Unique ID for the person
    int counter = 0;// Counter used to assign unique IDs

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
    public Gender getGender() { return gender ;}
    public int getID(){
        return id;
    }
    public String getBirthday(){
    return birthday;
    }
    public void setBalance(int balance){
        this.balance = balance;
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
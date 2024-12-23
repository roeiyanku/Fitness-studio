/**
 *Person class
*/
/*public enum Gender {
    Male,
    Female
}
*/
public class Person {
    private Gender gender;
    int age;
    String birthday;
    String name;
    int balance;
    int id;






//Constructor

    public Person(String name, int balance, Gender gender, String birthday, int id){
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthday = birthday;
        this.id = id;

    }


    //Getters and Setters

    public String getName(){
        return name;
    }

    public int getMoney(){
        return balance;
    }
    public void setMoney(int balance){
        this.balance = balance;
    }

    public String getBirthday(){
        return birthday;
    }

    public int getAge(){
        return age;
    }
     public int getId(){
        return id;
     }

    @Override
    public String toString(){
        return "P";
    }

    //    // Calculate age based on birth date
    //    private int calculateAge() {
    //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    //        LocalDate birthDate = LocalDate.parse(this.birthday, formatter);
    //        LocalDate currentDate = LocalDate.now();
    //        Period period = Period.between(birthDate, currentDate);
    //        return period.getYears();
    //    }
    //
    //    // toString Method
    //    @Override
    //    public String toString() {
    //        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d",
    //                id, name, gender, birthday, calculateAge(), money);
    //    }

    //Need to create a method to calculate the age of the person.


}
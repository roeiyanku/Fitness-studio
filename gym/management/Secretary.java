package gym.management;
import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static gym.management.Gym.clients;
import static gym.management.Sessions.ForumType.getFilteredForumList;

/**
 * The Secretary class represents the secretary of the gym, responsible for administrative tasks
 * such as client registration, session management, notifications, and payroll.
 * This class implements the NotificationSubject interface for sending notifications.
 */
public class Secretary extends Person implements NotificationSubject {

    protected boolean isWorking;//Indicates if the secretary is actively employed
    private static Secretary instance;// Singleton instance of Secretary
    int salary; // Monthly salary of the secretary
    String role = "gym.management.Secretary"; //Role description

    //Constructor to create a new secretary.
    public Secretary(Person person, int salary) {
        this.isWorking = true;
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.salary = salary;
    }


    //Sends a notification to all clients.
    public void notify(String message) {
        for (Client client : clients.values()) {
            client.getmessages(message,this);
        }
        addAction("A message was sent to all gym clients: " + message );
    }

     //Sends a notification to all clients registered for a specific session.
    public void notify(Session session, String message) {
                for (Client client : session.getParticipants().values()) {
                    client.getmessages(message,this);
                }
                addAction("A message was sent to everyone registered for session " + session.getType() + " on " + session.getDate() + "T" + session.getTime() + " :" + message );



    }

    // Sends a notification to all clients registered for sessions on a specific date.
    public void notify(String date, String message) {
        for (Session session: gym.management.Gym.sessions.values()) {
            if (session.getDate().equals(date)){
                for (Client client : session.getParticipants().values()) {
                    client.getmessages(message,this);
                }
            }
        }
        addAction("A message was sent to everyone registered for a session on " + date + " : " + message );
    }

    //Registers a new client to the gym.
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (gym.management.Gym.clients.containsKey(person.getID())) { // Check if the client is already registered
       //   throw new DuplicateClientException("Error: The client is already registered");
        }
        if (person.calculateAge() < 18) { // Check if the client is old enough
           // throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }

        Client newClient = new Client(person);
        gym.management.Gym.clients.put(newClient.getID(), newClient); // Add the new client to the map
        addAction("Registered new client: " + newClient.getName());
        return newClient;
    }





    // Unregisters a client from the gym.
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
            if (!gym.management.Gym.clients.containsKey(client.getID())) {
            //    throw new ClientNotRegisteredException();
            }
        gym.management.Gym.clients.remove(client.getID());
            addAction("Unregistered client: " + client.getName());

    }

    // Hires a new instructor for the gym.
    public Instructor hireInstructor(Person person, int salary, ArrayList<String> certifiedTypes){
            Instructor newInstructor = new Instructor(person, salary, certifiedTypes);
            gym.management.Gym.employees.put(newInstructor.getID(), newInstructor);
            addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary );
            return newInstructor;
    }

    // Adds a new session to the gym.
    public Session addSession(String sessionTypeClass, String dateandTime, ForumType forum, Instructor instructor) throws  InstructorNotQualifiedException {
            if (!instructor.getcertified().contains(sessionTypeClass)) {
            //    throw new InstructorNotQualifiedException();
            }
            //Split the date and time
            String[] parts = splitDateandTime(dateandTime);
            String date = parts[0]; //take date
            String time = parts[1]; //take time

            // Create a new session using a factory method
            Session newSession = SessionFactory.createSession(sessionTypeClass, date, time, forum, instructor);
            Gym.sessions.put(newSession.getSessionID(), newSession);

            addAction("Created new session: " + sessionTypeClass + " on " + date + "T" + time + " with instructor: " + instructor.getName() );
            return newSession;
    }

    // Splits the provided date and time string into separate date and time components.
    protected String [] splitDateandTime(String dateandtime){
        //split date and time string into an array
        String[] parts = dateandtime.split(" ");
        return parts;
    }

    // Registers a client for a session.
    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException  {
        // Check various conditions before registration
        if (client.isDeleted()){
           // throw new ClientNotRegisteredException();
        }
        if (clients.containsKey(client.getID())){
         //   throw new DuplicateClientException("Error: The client is already registered");
        }

        if (session.getCurrentSizeParticipants() >= session.getMaxParticipants()){
            addAction("Failed registration: No available spots for session");
        }

        if (client.getBalance() <= session.getPrice() ){
            addAction("Failed registration: gym.customers.Client doesn't have enough balance");

        }

        if  (!(getFilteredForumList(client).contains("Seniors") && (session.getForum().equals(ForumType.Seniors))) ){
            addAction("Failed registration: gym.customers.Client doesn't meet the age requirements for this session (Seniors)");
        }

        if  (getFilteredForumList(client).contains("Male") && (session.getForum().equals(ForumType.Female))){
            addAction("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements");
        }

        if  (getFilteredForumList(client).contains("Female") && (session.getForum().equals(ForumType.Male))){
            addAction("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements");
        }

        if  (!parseDate(session.getDate()).isAfter(parseDate(currentDate()))){
            addAction("Failed registration: gym.management.Sessions.Session is not in the future");
        }

        else // Proceed with registration if all checks pass
                {session.addParticipants(client,this);
                client.addSession(session,this);
                client.setBalance(client.getBalance() - session.getPrice());
                Gym.getInstance().setBalance(Gym.getInstance().getBalance() + session.getPrice());

                addAction("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + "T" +session.getTime() + " for price: " + session.getPrice());}
    }

     // remove a client from a session.
        public void unregisterClientFromLesson (Client client, Session session) throws  ClientNotRegisteredForLessonException {
                if (session.getParticipants().containsKey(client.getID())) {
                   throw new ClientNotRegisteredForLessonException();
                }
                session.removeParticipants(client,this);
                client.removeSession(session,this);
                addAction("Unregistered client: " + client.getName() + " from session: " + session.getType());
        }

    //Pays salaries to all employees (secretary and instructors).
    // This method ensures that only the secretary can initiate payments.
        public void paySalaries ()  {
                // Get the gym instance
                Gym gym = Gym.getInstance();
                LocalDateTime currentDate = LocalDateTime.now();

                // Pay all employees (both secretary and instructors)
                for (Person employee : gym.employees.values()) {
                    if (employee instanceof Secretary) {
                        // Pay secretary monthly salary
                        employee.balance += getSalary();
                        Gym.gymBalance -= getSalary();
                    } else if (employee instanceof Instructor) {
                        Instructor instructor = (Instructor) employee;
                        int totalPayment = 0;

                        // Check all sessions
                        for (Session session : Gym.sessions.values()) {
                            if (session.getInstructor().equals(instructor)) {
                                // Convert session date to LocalDateTime for comparison
                                LocalDate sessionDate = parseDate(session.getDate());

                                // Pay only for sessions that already happened
                                if (sessionDate.isBefore(ChronoLocalDate.from(currentDate))) {
                                    totalPayment += instructor.getSalary();
                                }
                            }
                        }
                        // Update instructor's balance
                        instructor.setBalance(instructor.getBalance() + totalPayment);
                    }
                }
                addAction("Salaries have been paid to all employees");
        }

        // Returns the salary of the secretary.
        public int getSalary () {
            return salary;
        }

        // Returns a string representation of the secretary's details.
        @Override
        public String toString() {
            return "ID: " + getID() + " | Name: " + getName() + " | gym.customers.Gender: " + getGender() +
                    " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                    " | Balance: " + getBalance() + " | Role: " + role + " | Salary per Month: " + getSalary();
        }

        //Adds an action to the gym's action history.
        private void addAction (String action)  {
                    Gym.actionsHistory.add(action);
            }

        // Prints all actions recorded in the gym's action history.
        public void printActions ()  {
            {
                for (String action : Gym.actionsHistory) {
                    System.out.println(action);
                }
            }
        }
    // Returns the current date as a string in the format "dd-MM-yyyy".
    protected String currentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return today.format(f);
    }
    // Parses a string representing a date in the format "dd-MM-yyyy" into a LocalDateTime object.
    protected LocalDateTime parseDateandTime(String datetime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(datetime, f);
    }
    protected LocalDate parseDate(String date) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, f);
    }

}




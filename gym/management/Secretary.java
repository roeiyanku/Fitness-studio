package gym.management;
import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static gym.management.Sessions.ForumType.getFilteredForumList;

/**
 * gym.management.Secretary class
 */



public class Secretary extends Person implements NotificationSubject {

    protected boolean isWorking;
    private static Secretary instance; //Singleton use
    int salary;
    String role = "gym.management.Secretary";


    public Secretary(Person person, int salary) {
        this.isWorking = true;
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.salary = salary;
    }


    public static Secretary getInstance(Person person, int salary) {
        if (instance == null) {
            instance = new Secretary(person, salary);
        }
        return instance;
    }


    //A message for all clients
    public void notify(String message) throws FormerSecretaryNotAllowedException {
        if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
                for (Client client : Gym.clients.values()) {
                    Client.addNotifications(message);
                }
                addAction("A message was sent to all gym clients: " + message + "\n");

        }
    }
    //A message for all clients in Session
    public void notify(Session session, String message)  throws FormerSecretaryNotAllowedException {
        if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
            for (Client client : session.getParticipants().values()) {
                Client.addNotifications(message);
            }
            addAction("A message was sent to everyone registered for session " + session.getType() + " on " + session.getDate() + "T" + session.getTime() + " :" + message + "\n");
        }
    }
    public void notify(String date, String message) throws FormerSecretaryNotAllowedException {
        if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
        for (Session session: Gym.sessions.values()) {
            if (session.getDate().equals(date)){
                for (Client client : session.getParticipants().values()) {
                    Client.addNotifications(message);
                }
            }
        }
        addAction("A message was sent to everyone registered for a session on " + date + " : " + message  +"\n");
        }
    }


    public Client registerClient(Person person) throws FormerSecretaryNotAllowedException, InvalidAgeException, DuplicateClientException, ClientAlreadyRegisteredException {
        if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
        if (!Gym.getInstance().clients.containsKey(person.getID())) {
            throw new ClientAlreadyRegisteredException();
        }
        if (person.calculateAge() < 18) {
            throw new InvalidAgeException();
        } else {
            for (Client client : Gym.clients.values()) {
                if (client.getID() == (person.getID())) {
                    throw new DuplicateClientException();
                }
            }
            Client newclient = new Client(person);
            Gym.clients.put(newclient.getID(), newclient);
            addAction("Registered new client: " + newclient.getName()  +"\n");
            return newclient;
        }
        }
    }


    public void unregisterClient(Client client) throws FormerSecretaryNotAllowedException, ClientNotRegisteredException {
        if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
        if (!Gym.clients.containsKey(client.getID())) {
            throw new ClientNotRegisteredException();
        }
        Gym.clients.remove(client.getID());
        addAction("Unregistered client: " + client.getName()  +"\n");
    }}


    public Instructor hireInstructor(Person person, int salary, ArrayList<String> certifiedTypes) throws FormerSecretaryNotAllowedException{
        if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
            Instructor newInstructor = new Instructor(person, salary, certifiedTypes);
            Gym.employees.put(newInstructor.getID(), newInstructor);
            addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary + "\n");
            return newInstructor;
        }
    }


    public Session addSession(String sessionTypeClass, String dateandTime, ForumType forum, Instructor instructor) throws FormerSecretaryNotAllowedException, InstructorNotQualifiedException {
        if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
            if (!instructor.getcertified().contains(sessionTypeClass)) {
                throw new InstructorNotQualifiedException();
            }
            String[] parts = splitDateandTime(dateandTime);
            String date = parts[0]; //take date
            String time = parts[1]; //take time

            Session newSession = SessionFactory.createSession(sessionTypeClass, date, time, forum, instructor);
            Gym.sessions.put(newSession.getSessionID(), newSession);
            addAction("Created new session: " + sessionTypeClass + " on " + date + "T" + time + " with instructor: " + instructor.getName() + "\n");
            return newSession;
        }
    }

    protected String [] splitDateandTime(String dateandtime){
        //split date and time
        String[] parts = dateandtime.split(" ");
        return parts;
    }

    public void registerClientToLesson(Client client, Session session) throws FormerSecretaryNotAllowedException, ClientAlreadyRegisteredForLessonException  {
        if (!client.isDeleted()){
        if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
        if (session.getParticipants().containsKey(client.getID())) {
            throw new ClientAlreadyRegisteredForLessonException();
        }
        if (session.getCurrentSizeParticipants() < session.getMaxParticipants()){
            addAction("Failed registration: No available spots for session\n");
        }

        if (client.getBalance() >= session.getPrice() ){
            addAction("Failed registration: gym.customers.Client doesn't have enough balance\n");

        }

        if  (!getFilteredForumList(client).contains("Seniors") && (session.getForum().equals(ForumType.Seniors)) ){
            addAction("Failed registration: gym.customers.Client doesn't meet the age requirements for this session (Seniors)\n");
        }

        if  (getFilteredForumList(client).contains("Male") && (session.getForum().equals(ForumType.Female))){
            addAction("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements\n");
        }

        if  (getFilteredForumList(client).contains("Female") && (session.getForum().equals(ForumType.Male))){
            addAction("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements\n");
        }

        if  (!parseDate(session.getDate()).isAfter(parseDate(currentDate()))){
            addAction("Failed registration: gym.management.Sessions.Session is not in the future\n");
        }


        else {session.addParticipants(client);
                client.addSession(session);

                client.setBalance(client.getBalance() - session.getPrice());
                Gym.getInstance().setBalance(session.getPrice());

                addAction("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + "T" +session.getTime() + " for price: " + session.getPrice() +"\n");}
        }
    }}


        public void unregisterClientFromLesson (Client client, Session session) throws FormerSecretaryNotAllowedException, ClientNotRegisteredForLessonException {
            if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
                if (session.getParticipants().containsKey(client.getID())) {
                    throw new ClientNotRegisteredForLessonException();
                }
                session.removeParticipant(client);
                client.removeSession(session);
                addAction("Unregistered client: " + client.getName() + " from session: " + session.getType() + "\n");
            }
        }


        public void paySalaries () throws FormerSecretaryNotAllowedException {
            if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
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
                                LocalDateTime sessionDate = parseDate(session.getDate());

                                // Pay only for sessions that already happened
                                if (sessionDate.isBefore(currentDate)) {
                                    totalPayment += instructor.getSalary();
                                }
                            }
                        }
                        // Update instructor's balance
                        instructor.setBalance(instructor.getBalance() + totalPayment);
                    }
                }
                addAction("Salaries have been paid to all employees\n");
            }
        }

        public int getSalary () {
            return salary;
        }


        //for example: ID: 1113 | Name: Maayan | gym.customers.Gender: Female | Birthday: 21-12-2005 | Age: 19 | Balance: 50 | Role: gym.management.Secretary | Salary per Month: 8000
        @Override
        public String toString () {
            return "ID: " + getID() + " | Name: " + getName() + " | gym.customers.Gender: " + getGender() +
                    " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                    " | Balance: " + getBalance() + " | Role: " + role + " | Salary per Month: " + getSalary() +"\n";
        }

        private void addAction (String action) throws FormerSecretaryNotAllowedException{
            if (!isWorking){throw new FormerSecretaryNotAllowedException();} {
                Gym.actionsHistory.add(action);
            }
        }

        public void printActions () throws FormerSecretaryNotAllowedException{
            if (!isWorking){throw new FormerSecretaryNotAllowedException();}{
            for (String action : Gym.actionsHistory) {

                    System.out.println(action);
                }
            }
        }

    protected String currentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return today.format(f);
    }
    protected LocalDateTime parseDate(String date) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDateTime.parse(date, f);
    }

}




package gym.management;
import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionTypes.SessionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static gym.management.Sessions.ForumType.getFilteredForumList;

/**
 * gym.management.Secretary class
 */



public class Secretary extends Person implements NotificationSubject {


    private static Secretary instance; //Singleton use
    int salary;
    String role = "gym.management.Secretary";


    public Secretary(Person person, int salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.salary = salary;
    }


    public static Secretary getInstance(Person person, int salary) {
        if (instance == null) {
            instance = new Secretary(person, salary);
        }
        return instance;
    }


    //A message for client
    public void notify(Client client, String message) {
        Client.getNotifications(message);
        addAction("Sent notification to client: " + message);
    }
    //A message for all clients
    public void notify(String message) {
        for (Client client : Gym.clients.values()) {
            Client.getNotifications(message);
        }
        addAction("Sent notification to all clients: " + message);
    }
    //A message for all clients in Session
    public void notify(Session session, String message) {
        for (Client client : session.getParticipants().values()) {
            Client.getNotifications(message);
        }
        addAction("Sent notification to client for session: " + message);
    }
    public void notify(String date, String message) {
        // if the date is in format "dd-MM-yyyy"
        Map<Session, List<NotificationObserver>> sessionsForDay = daySessionClients.get(date);
        if (sessionsForDay != null) {
            for (Map.Entry<Session, List<NotificationObserver>> entry : sessionsForDay.entrySet()) {
                for (NotificationObserver observer : entry.getValue()) {
                    Client.getNotifications(message);
                }
            }
        }
        addAction("Sent notification for date " + date + ": " + message);
    }


    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException, ClientAlreadyRegisteredException {
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
            addAction("Registered new client: " + newclient.getName());
            return newclient;
        }
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!Gym.clients.containsKey(client.getID())) {
            throw new ClientNotRegisteredException();
        }
        Gym.clients.remove(client.getID());
        addAction("Unregistered client: " + client.getName());
    }


    public Instructor hireInstructor(Person person, int salary, ArrayList<String> certifiedTypes) {
        Instructor newInstructor = new Instructor(person, salary, certifiedTypes);
        addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        return newInstructor;
    }


    public Session addSession(String sessionTypeClass, String date, ForumType forum, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getcertified().contains(sessionTypeClass.getName())) {
            throw new InstructorNotQualifiedException();
        }

        Session newSession = SessionFactory.createSession(sessionTypeClass, date, forum, instructor);
        Gym.sessions.put(newSession.getSessionID(), newSession);
        addAction("Created new session: " + sessionTypeClass + " on " + date + " with instructor: " + instructor.getName());
        return newSession;
    }

    public void registerClientToLesson(Client client, Session session) throws ClientAlreadyRegisteredForLessonException {
        if (session.getParticipants().containsKey(client.getID())) {
            throw new ClientAlreadyRegisteredForLessonException();
        }
        if (session.getCurrentSizeParticipants() < session.getMaxParticipants() &&
            (client.getBalance() >= session.getPrice() ) &&
                (!getFilteredForumList(client).contains(session.getForum())) &&
                (!parseDate(session.getDate()).isAfter(parseDate(currentDate()))){

                session.addParticipants(client);
                client.addSession(session);

                client.setBalance(client.getBalance() - session.getPrice());
                Gym.getInstance().addBalance(session.getPrice());

                addAction("Registered client: " + client.getName() + " to session: " + session.getName());

        }
    }


        public void unregisterClientFromLesson (Client client, Session session) throws ClientNotRegisteredForLessonException {
            if (session.getParticipants().containsKey(client.getID())){
                throw new ClientNotRegisteredForLessonException();
            }
            session.removeParticipant(client);
            client.removeSession(session);
            addAction("Unregistered client: " + client.getName() + " from session: " + session.getName());
        }


        private void paySalaries () {

        }

        public int getSalary () {
            return salary;
        }


        //for example: ID: 1113 | Name: Maayan | gym.customers.Gender: Female | Birthday: 21-12-2005 | Age: 19 | Balance: 50 | Role: gym.management.Secretary | Salary per Month: 8000
        @Override
        public String toString () {
            return "ID: " + getID() + " | Name: " + getName() + " | gym.customers.Gender: " + getGender() +
                    " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                    " | Balance: " + getBalance() + " | Role: " + role + " | Salary per Month: " + getSalary();
        }

        private void addAction (String action){
            Gym.actionsHistory.add(action);
        }

        public void printActions () {
            for (String action : Gym.actionsHistory) {
                System.out.println(action);
            }
        }
    protected String currentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return now.format(f);
    }
    protected LocalDateTime parseDate(String date) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(date, f);
    }

}




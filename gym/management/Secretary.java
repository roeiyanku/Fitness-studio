package gym.management;
import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static gym.management.Gym.gymBalance;
import static gym.management.Sessions.ForumType.getFilteredForumList;
import static gym.management.Sessions.SessionFactory.createSession;

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


    //A message for all clients
    @Override
    protected void notifyClient(String message) {
        for (Client client : Gym.clients.values()) {
            Client.getNotifications(message);
        }
        addAction("Sent notification to all clients: " + message);
    }

    //A message for all clients in Session
    protected void notifyClientForSession(Session session, String message) {
        for (Client client : session.getParticipants().values()) {
            Client.getNotifications(message);
        }
        addAction("Sent notification to client for session: " + message);
    }


    protected void notifyClientsForSessionAndDay(String day, Session session, String message) {
        Map<Session, List<NotificationObserver>> sessionMap = daySessionClients.get(day);
        if (sessionMap != null) {
            List<NotificationObserver> clientsForSession = sessionMap.get(session);
            if (clientsForSession != null) {
                for (NotificationObserver observer : clientsForSession) {
                    Client.getNotifications(message);
                }
            }
        }
    }


    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException, ClientAlreadyRegisteredException {
        if (!Gym.clients.containsKey(person.getID())) {
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

    protected void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!Gym.clients.containsKey(client.getID())) {
            throw new ClientNotRegisteredException();
        }
        Gym.clients.remove(client.getID());
        addAction("Unregistered client: " + client.getName());
    }


    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> certifiedTypes) {
        Instructor newInstructor = new Instructor(person, salary, certifiedTypes);
        addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        return newInstructor;
    }


    protected Session addSession(SessionType type, String date, ForumType forum, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getcertified().contains(type)) {
            throw new InstructorNotQualifiedException();
        }
        Session newSession = createSession(type, date, forum, instructor);
        Gym.sessions.put(newSession.getSessionID(), newSession);
        addAction("Created new session: " + type + " on " + date + " with instructor: " + instructor.getName());
        return newSession;
    }

    protected void registerClientToLesson(Client client, Session session) throws ClientAlreadyRegisteredForLessonException {
        if (session.getParticipants().containsKey(client.getID())) {
            throw new ClientAlreadyRegisteredForLessonException();
        }
        if (session.getCurrentSizeParticipants() < session.getMaxParticipants() &&
            (client.getBalance() >= session.getPrice() ) &&
                (getFilteredForumList(client).contains(session.getForum())) &&
                (session.getDate() >= currentDate)){



                session.addParticipant(client);
                client.addSession(session);
                session.getCurrentSizeParticipants()++;


                client.balance -= session.getPrice();
                Gym.gymBalance += session.getPrice();


                addAction("Registered client: " + client.getName() + " to session: " + session.getSessionType());

        }
         throw new Execption;
    }


        public void unregisterClientFromLesson (Client client, Session session) throws ClientNotRegisteredForLessonException {
            if (session.getParticipants().containsKey(client.getID())){
                throw new ClientNotRegisteredForLessonException();
            }
            session.removeParticipant(client);
            client.removeSession(session);
            addAction("Unregistered client: " + client.getName() + " from session: " + session.getType());
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
}




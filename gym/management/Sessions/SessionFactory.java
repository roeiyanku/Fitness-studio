package gym.management.Sessions;
import gym.management.Instructor;

public class SessionFactory {
    public static Session createSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) {
        int price = sessionType.getPrice();
        int capacity = sessionType.getCapacity();

        return new Session(price, capacity, date,  forumType, instructor);
    }
}

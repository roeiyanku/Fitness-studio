package gym.management;
import gym.Exception.*;
import gym.management.Sessions.Session;

/**
 * The NotificationSubject interface defines methods for notifying different entities
 * with various types of messages. It ensures consistent notification behavior across
 * classes that implement it.
 */
public interface NotificationSubject {
        void notify (String message) throws FormerSecretaryNotAllowedException; //Sends a notification message for all clients.
        void notify(Session session, String message) throws FormerSecretaryNotAllowedException; //Sends a notification related to a specific session.
        void notify(String date, String message) throws FormerSecretaryNotAllowedException;//Sends a notification with a specific date.
}


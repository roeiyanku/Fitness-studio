// SessionFactory.java
package gym.management.Sessions;

import gym.management.Sessions.SessionTypes.*;
import gym.management.Instructor;
/**
 * Factory method that creates a session based on the session type class.
 * This method takes the session type (e.g., Pilates, MachinePilates) and other session details,
 * then returns the appropriate session object.
 */
  public class SessionFactory {
    public static Session createSession(String sessionTypeClass, String date, String time, ForumType forum, Instructor instructor) {
        switch(sessionTypeClass){
            case SessionType.MachinePilates:
                return new MachinePilates("MachinePilates", date, time, forum, instructor);
            case SessionType.Pilates:
                return new Pilates("Pilates", date, time, forum, instructor);
            case SessionType.Ninja:
                return new Ninja("Ninja", date, time, forum, instructor);
            case SessionType.ThaiBoxing:
                return new ThaiBoxing("ThaiBoxing", date, time, forum, instructor);
            default:
                // Return null if the session type is not recognized
                return null;

        }
    }
}

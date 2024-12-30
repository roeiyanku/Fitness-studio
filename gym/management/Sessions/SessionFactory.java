// SessionFactory.java
package gym.management.Sessions;

import gym.management.Sessions.SessionTypes.*;
import gym.management.Instructor;

public class SessionFactory {
    public static Session createSession(String sessionTypeClass, String date, ForumType forum, Instructor instructor) {
        switch(sessionTypeClass){
            case SessionType.MachinePilates:
                return new MachinePilates("MachinePilates", date, forum,  instructor);
            case SessionType.Pilates:
                return new Pilates("Pilates", date, forum,  instructor);
            case SessionType.Ninja:
                return new Ninja("Ninja", date, forum,  instructor);
            case SessionType.ThaiBoxing:
                return new ThaiBoxing("ThaiBoxing", date, forum,  instructor);

        }
    }
}

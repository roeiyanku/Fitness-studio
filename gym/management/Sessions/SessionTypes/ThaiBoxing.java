package gym.management.Sessions.SessionTypes;

import gym.management.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionTypes.SessionType;

public class ThaiBoxing extends Session {

    // sessionPrice = 100;
    // maxParticipants = 20;

    public ThaiBoxing(String type, String date, ForumType forumType, Instructor instructor) {
        super(type, 100, 20,  date, forumType, instructor);
    }


}

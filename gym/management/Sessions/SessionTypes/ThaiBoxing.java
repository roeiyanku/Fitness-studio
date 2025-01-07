package gym.management.Sessions.SessionTypes;

import gym.management.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

public class ThaiBoxing extends Session {

    // sessionPrice = 100;
    // maxParticipants = 20;

    public ThaiBoxing(String type, String date, String time, ForumType forumType, Instructor instructor) {
        super(type, 100, 20,  date, time, forumType, instructor);
    }


}

package gym.management.Sessions.SessionTypes;

import gym.management.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionTypes.SessionType;

public class Pilates extends Session {

    // sessionPrice = 60;
    // maxParticipants = 30;

    public Pilates(String type, String date, ForumType forumType, Instructor instructor) {
        super(type, 60, 30,  date, forumType, instructor);
    }


}

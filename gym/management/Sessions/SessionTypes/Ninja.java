package gym.management.Sessions.SessionTypes;

import gym.management.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionTypes.SessionType;

public class Ninja extends Session {

    // sessionPrice = 150;
    // maxParticipants = 5;

    public Ninja(String type, String date, ForumType forumType, Instructor instructor) {
        super(type, 150, 5,  date, forumType, instructor);
    }


}

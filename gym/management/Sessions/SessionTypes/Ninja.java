package gym.management.Sessions.SessionTypes;

import gym.management.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

public class Ninja extends Session {

    // sessionPrice = 150;
    // maxParticipants = 5;

    public Ninja(String type, String date, String time, ForumType forumType, Instructor instructor) {
        super(type, 150, 5,  date, time,  forumType, instructor);
    }


}

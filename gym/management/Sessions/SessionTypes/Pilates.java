package gym.management.Sessions.SessionTypes;

import gym.management.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

public class Pilates extends Session {

    // sessionPrice = 60;
    // maxParticipants = 30;

    public Pilates(String type, String date, String time, ForumType forumType, Instructor instructor) {
        super(type, 60, 30, date, time, forumType, instructor);
    }


}

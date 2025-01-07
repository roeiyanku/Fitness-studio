package gym.management.Sessions.SessionTypes;

import gym.management.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

public class MachinePilates extends Session {

    // sessionPrice = 80;
    // maxParticipants = 10;

    public MachinePilates(String type, String date, String time, ForumType forumType, Instructor instructor) {
        super(type, 80, 10,  date, time, forumType, instructor);
    }


}

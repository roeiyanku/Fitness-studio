package gym.management.Sessions;
import gym.customers.*;
import java.util.ArrayList;
import java.util.List;

public enum ForumType {
    All,
    Seniors,
    Female,
    Male;

    public static List<ForumType> getFilteredForumList(Client client) {
        List<ForumType> forums = new ArrayList<>();

        forums.add(All);

        if (client.calculateAge() >= 65) {
            forums.add(Seniors);
        }

        if (client.getGender() == Gender.Female) {
            forums.add(Female);
        } else if (client.getGender() == Gender.Male) {
            forums.add(Male);
        }

        return forums;
    }
}

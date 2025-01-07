package gym.management.Sessions;
import gym.customers.*;
import java.util.ArrayList;
import java.util.List;

// Enum defining the types of forums (groups of customers with similar criteria)

public enum ForumType {
    All,
    Seniors,
    Female,
    Male;
    // returns a list of forums that fit a customer
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

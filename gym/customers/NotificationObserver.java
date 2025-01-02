package gym.customers;
import java.util.ArrayList;

public interface NotificationObserver {
    String getNotifications();
    void addNotifications(String message);
}

package gym.management;

import gym.customers.Client;
import gym.management.Sessions.Session;

public interface NotificationSubject {
        void registerClient (Client client);
        void unregisterClient (Client client);
        void notifyClient (String message);
        void notifyClientForSession (String message);
}


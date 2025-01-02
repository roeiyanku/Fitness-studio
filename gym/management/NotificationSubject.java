package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;

public interface NotificationSubject {
        void registerClient (Person person)throws FormerSecretaryNotAllowedException, InvalidAgeException, DuplicateClientException, ClientAlreadyRegisteredException;
        void unregisterClient (Client client) throws FormerSecretaryNotAllowedException, ClientNotRegisteredException; ;
        void notify (String message) throws FormerSecretaryNotAllowedException;
        void notify(Session session, String message) throws FormerSecretaryNotAllowedException;
        void notify(String date, String message) throws FormerSecretaryNotAllowedException;
}


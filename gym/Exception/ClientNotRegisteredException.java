package gym.Exception;

public class ClientNotRegisteredException extends Exception {
            public ClientNotRegisteredException() {
                super("Error: The client is not registered and cannot perform this action");
            }
        }

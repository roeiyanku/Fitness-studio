package gym.Exception;

public class ClientAlreadyRegisteredException extends Exception {
            public ClientAlreadyRegisteredException() {
                super("Error: The client is already registered");
            }
        }

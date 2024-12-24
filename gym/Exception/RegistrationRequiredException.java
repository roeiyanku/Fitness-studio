package gym.Exception;

public class RegistrationRequiredException extends Exception {
            public RegistrationRequiredException() {
                super("Error: Registration is required before attempting to unregister");
            }
        }

package gym.Exception;

public class ClientAlreadyRegisteredForLessonException extends Exception {
            public ClientAlreadyRegisteredForLessonException() {
                super("Error: The client is already registered for this lesson");
            }
        }

package gym.Exception;

public class ClientNotRegisteredForLessonException extends Exception {
            public ClientNotRegisteredForLessonException() {
                super("Error: The client is not registered with the gym and cannot enroll in lessons");
            }
        }

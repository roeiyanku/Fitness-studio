package gym;


    public class Exception{
        public Exception(String message) {
            super(message);
        }
    }


        // Registration errors:
        public class AgeNotAllowedException{
            public AgeNotAllowedException() {
                super("Error: Client must be at least 18 years old to register");
            }
        }

        public class ClientAlreadyRegisteredException extends Exception {
            public ClientAlreadyRegisteredException() {
                super("Error: The client is already registered");
            }
        }

        public class RegistrationRequiredException extends Exception {
            public RegistrationRequiredException() {
                super("Error: Registration is required before attempting to unregister");
            }
        }

        //Training and coaching errors:
        public class InstructorNotQualifiedException extends Exception {
            public InstructorNotQualifiedException() {
                super("Error: Instructor is not qualified to conduct this session type.");
            }
        }

        public class ClientAlreadyRegisteredForLessonException extends Exception {
            public ClientAlreadyRegisteredForLessonException() {
                super("Error: The client is already registered for this lesson");
            }
        }

        public class ClientNotRegisteredForLessonException extends Exception {
            public ClientNotRegisteredForLessonException() {
                super("Error: The client is not registered with the gym and cannot enroll in lessons");
            }
        }

        //Secretary errors:
        public class FormerSecretaryNotAllowedException extends Exception {
            public FormerSecretaryNotAllowedException() {
                super("Error: Former secretaries are not permitted to perform actions");
            }
        }

        //General errors:
        public class DuplicateClientException extends Exception {
            public DuplicateClientException() {
                super("Error: The client is already registered");
            }
        }
        public class InvalidAgeException extends Exception {
            public InvalidAgeException() {
                super("Error: Client must be at least 18 years old to register");
            }
        }
        public class ClientNotRegisteredException extends Exception {
            public ClientNotRegisteredException() {
                super("Error: The client is not registered and cannot perform this action");
            }
        }














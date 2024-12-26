package gym.Exception;

// Registration errors:
        public class AgeNotAllowedException extends Exception {
            public AgeNotAllowedException() {
                super("Error: gym.customers.Client must be at least 18 years old to register");
            }
        }

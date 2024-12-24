package gym.Exception;

//General errors:
        public class DuplicateClientException extends Exception {
            public DuplicateClientException() {
                super("Error: The client is already registered");
            }
        }

package gym.Exception;

//Secretary errors:
        public class FormerSecretaryNotAllowedException extends Exception {
            public FormerSecretaryNotAllowedException() {
                super("Error: Former secretaries are not permitted to perform actions");
            }
        }

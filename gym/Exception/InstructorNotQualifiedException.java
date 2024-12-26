package gym.Exception;

//Training and coaching errors:
        public class InstructorNotQualifiedException extends Exception {
            public InstructorNotQualifiedException() {
                super("Error: gym.management.Instructor is not qualified to conduct this session type.");
            }
        }

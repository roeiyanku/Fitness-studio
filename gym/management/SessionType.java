package gym.management;

public enum SessionType {

    Pilates { // Pilates: price - 60 / capacity: 30

        public int getPrice() {
            return 60;
        }


        public int getCapacity() {
            return 30;
        }
    },

    MachinePilates { // MachinePilates: price - 80 / capacity: 10

        public int getPrice() {
            return 80;
        }


        public int getCapacity() {
            return 10;
        }
    },

    ThaiBoxing { // ThaiBoxing: price - 100 / capacity: 10

        public int getPrice() {
            return 100;
        }


        public int getCapacity() {
            return 10;
        }
    },

    Ninja { // Ninja: price - 150 / capacity: 5

        public int getPrice() {
            return 150;
        }


        public int getCapacity() {
            return 5;
        }
    };

    // Abstract methods to be implemented by each constant
    public abstract int getPrice();

    public abstract int getCapacity();
}

package java2_Homework_1;

public class Main {

    public static void main(String[] args) {

        Participants[] participant = new Participants[4];
        participant[0] = new Human("Даниил", 3000, 1.5);
        participant[1] = new Human("Валерия", 2500, 1.1);
        participant[2] = new Cat("Санька", 500, 2.0);
        participant[3] = new Robot("R2D2", 10000, 0);

        Obstacle[] obstacle = new Obstacle[4];
        obstacle[0] = new Treadmill(2600.0);
        obstacle[1] = new Wall(1.0);
        obstacle[2] = new Wall(1.5);
        obstacle[3] = new Wall(1.7);

        doExercises(obstacle, participant);

    }

    public static void doExercises(Obstacle[] obstacle, Participants[] participant){

        for (int i = 0; i < participant.length; i++) {
            for (int j = 0; j < obstacle.length; j++) {

                if (obstacle[j] instanceof Treadmill) {
                    participant[i].run();
                    if (obstacle[j].checkPassing(participant[i].getDistance()) == false) break;
                } else {
                    participant[i].jump();
                    if (obstacle[j].checkPassing(participant[i].getHeight()) == false) break;
                }
            }

        }
    }
}

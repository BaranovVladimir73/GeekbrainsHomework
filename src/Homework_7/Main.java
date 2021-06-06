package Homework_7;

public class Main {

    public static void main(String[] args) {
		Cat [] cat = new Cat[5];
		cat[0] = new Cat("Барсик", 5, false);
		cat[1] = new Cat("Васька", 15, false);
		cat[2] = new Cat("Санька", 20, false);
		cat[3] = new Cat("Люська", 10, false);
		cat[4] = new Cat("Кузя", 30, false);

		Plate plate = new Plate(100);

		plate.info();
		System.out.println("Начинаем кормить котов");

		for (int i = 0; i < cat.length; i++) {
			cat[i].eat(plate);
			plate.info();
			cat[i].info();

		}

		plate.increaseFood(100);
		plate.info();

	}
}

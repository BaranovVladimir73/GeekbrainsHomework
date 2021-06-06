package Homework_7;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite, boolean satiety) {
        this.name = name;
        this.appetite = appetite;
        if (satiety) this.satiety = true;
        else {
            this.satiety = false;
        }
    }

    public String getName() {
        return name;
    }

    public void eat(Plate p){
        if (appetite < p.getFood()) {
            p.decreaseFood(appetite);
            satiety = true;
        }
        else satiety = false;
    }

    public void info(){
        System.out.println("Кот " + name + " не голоден? - " + satiety);
    }


}

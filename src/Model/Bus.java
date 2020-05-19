package Model;

public class Bus extends Car {
    private int speed; //segments moved per turn
    private int position; // position on current road
    private Road currentRoad; // current Model.Road object
    private static float breadth;


    public Bus(String id, Road road) {
        this.id = ("bus_" + id);
        length = super.getLength() * 3;
        breadth = super.getBreadth();
        this.currentRoad = road;
        this.currentRoad.getBusesOnRoad().add(this);
    }
    public void printBusStatus() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getId(), this.getPosition());
    }
    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        Car.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        Bus.breadth = breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad = currentRoad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}


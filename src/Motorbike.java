public class Motorbike extends Car {
    private int speed; //segments moved per turn
    private int position; // position on current road
    private Road currentRoad; // current Road object
    private static float breadth;

    public Motorbike(String id, Road road) {
        this.id = ("bike_" + id);
        length = super.getLength() * 0.5f;
        breadth = super.getBreadth();
        this.currentRoad = road;
        this.currentRoad.getBikesOnRoad().add(this);
    }
    public void printBikeStatus() {
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
        Motorbike.breadth = breadth;
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




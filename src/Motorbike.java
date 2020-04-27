public class Motorbike extends Car {

    public Motorbike(String id) {
        this.id = ("bike_" + id);
        length = super.getLength() * 0.5f;
    }
    public void printBikeStatus() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getId(), this.getPosition());
    }

}

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Object Random;

    public static void main(String[] args) {

        //Get info needed to start sim:
        Scanner simController = new Scanner(System.in);
        //Getting the user input to check prototype
        System.out.println("How many roads?");
        int roadSpawns = simController.nextInt();
        System.out.println("Road Type: 1)");
        System.out.println("How many cars?");
        int carSpawns = simController.nextInt();
        System.out.println("How many buses?");
        int busSpawns = simController.nextInt();
        System.out.println("How many motorbikes?");
        int bikeSpawns = simController.nextInt();
        System.out.println("How many traffic lights?");
        int lightSpawns = simController.nextInt();

        // set values for user inputs for prototype.
//        int roadSpawns = 2;
//        int carSpawns = 1;
//        int lightSpawns = 1;


        //Create objects:
        System.out.println("Object Creation:\n---------------------");
        System.out.println("Roads:" + roadSpawns);
        System.out.println("Cars:" + carSpawns);
        System.out.println("Traffic lights:" + lightSpawns);
        ArrayList<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            System.out.println("Please input parameters f or road_" + i + "...");
            System.out.print("Length:");
            int lengthInput = simController.nextInt();
//            System.out.print("Speed limit:");
//            int speedLimitInput = simController.nextInt();
            int speedLimitInput = 1; // force speed limit to be 1 for prototype.
                roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));

        }
        System.out.println("\nRoads;");
        for (Road road : roads) {
            road.printRoadInfo();
        }

        System.out.println("\nCars;");
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0)));// all created cars will begin on road_0.
            cars.get(i).printCarStatus();
        }

        System.out.println("\nBuses;");
        ArrayList<Bus> buses = new ArrayList<>();
        for (int i = 0; i < busSpawns; i++) {
            buses.add(new Bus(Integer.toString(i), roads.get(0)));// all created cars will begin on road_0.
            buses.get(i).printBusStatus();
        }

        System.out.println("\nTraffic Lights;");
        ArrayList<TrafficLight> lights = new ArrayList<>();
        Random random = new Random();
            for (int i = 0; i < lightSpawns; i++) {
                int j = random.nextInt(roadSpawns);
                lights.add(new TrafficLight(Integer.toString(i), roads.get(0))); // all created lights will begin on road_0.
                lights.get(i).printLightStatus();
        }
        System.out.println();


        // set locations and connections:
        System.out.println("Settings:");
        roads.get(1).setStartLocation(new int[]{roads.get(0).getLength() + 1, 0}); // place road_1 to a position at the end of road_0.
        roads.get(1).printRoadInfo();
        roads.get(0).getConnectedRoads().add(roads.get(1)); // connect road_0 to road_1
        System.out.println();


        //Simulation loop:
        System.out.println("Simulation:");
        Random r = new Random();
        int time = 0;
        System.out.print("Set time scale in milliseconds:");
        int speedOfSim = simController.nextInt();
        int carsFinished = 0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                light.operate(r.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.move();
                car.printCarStatus();
                if (car.getCurrentRoad().getConnectedRoads().isEmpty() && (car.getSpeed() == 0)) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }


    }
}

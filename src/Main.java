import Model.SaveFile;
import View.EditorPanel;
import View.SimulationPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.lightGray;
import static java.awt.Color.white;

public class Main {

    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 800;
    private static SimulationPanel simulationPanel = new SimulationPanel();
    private static EditorPanel editorPanel = new EditorPanel();
    private static SaveFile saveFile = new SaveFile();
    private static final int SCALE = 6;

    public static void main(String[] args) {
        // Simulation Window setup:
        JFrame mainWindow = new JFrame("Traffic Simulator");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        //Status Bar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0));
        bottomPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JLabel modeLabel = new JLabel("Mode: ");
        bottomPanel.add(modeLabel);
        JLabel statusLabel = new JLabel("Status: ");
        bottomPanel.add(statusLabel);
        mainWindow.add(bottomPanel, BorderLayout.SOUTH);

        //Menu bar:
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,0));
        JButton sim = new JButton("Simulator");
        JButton city = new JButton("City Editor");
        mainWindow.add(topPanel, BorderLayout.NORTH);

        //Side Bar
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(25, 1));
        sidePanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JButton newCity = new JButton("New");
        JButton open = new JButton("Open");
        JButton save = new JButton("Save");
        JButton exit = new JButton("Exit");
        mainWindow.add(sidePanel, BorderLayout.WEST);

        //West container(Simulator)
        JButton loadMap = new JButton("Load Map");
        JButton addVehicle = new JButton("Add Vehicle");
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton update = new JButton("Update Rate");
        mainWindow.add(sidePanel, BorderLayout.WEST);


        //Editor
        loadMap.setEnabled(false);
        addVehicle.setEnabled(false);
        start.setEnabled(false);
        stop.setEnabled(false);
        update.setEnabled(false);
        newCity.setEnabled(false);
        open.setEnabled(false);
        save.setEnabled(false);
        exit.setEnabled(false);

        topPanel.add(city);
        city.setBackground(lightGray);
        city.addActionListener(e -> {
            city.setBackground(new Color(204, 255, 229));
            sim.setBackground(lightGray);
            loadMap.setEnabled(false);
            loadMap.setBackground(white);
            addVehicle.setEnabled(false);
            addVehicle.setBackground(white);
            start.setEnabled(false);
            start.setBackground(white);
            stop.setEnabled(false);
            stop.setBackground(white);
            update.setEnabled(false);
            update.setBackground(white);
            newCity.setEnabled(true);
            newCity.setBackground(new Color(255,204,229));
            open.setEnabled(true);
            open.setBackground(new Color(255,204,229));
            save.setEnabled(true);
            save.setBackground(new Color(255,204,229));
            exit.setEnabled(true);
            exit.setBackground(new Color(255,204,229));
        });
        sidePanel.add(newCity);
        sidePanel.setBackground(lightGray);
        newCity.addActionListener(e -> {
            simulationPanel.setVisible(false);
            mainWindow.remove(editorPanel);
            editorPanel = new EditorPanel();
            editorPanel.newMap();
            editorPanel.setScale(SCALE);
            mainWindow.add(editorPanel);
            editorPanel.setVisible(true);
            statusLabel.setText("Status: New Map");
            mainWindow.validate();
            mainWindow.repaint();
        });

        sidePanel.add(open);
        open.addActionListener(e -> {
            Desktop desktop = Desktop.getDesktop();
            File file = new File("C:\\");
           try {
               desktop.open(file);
           } catch (IOException e1){
               e1.printStackTrace();
           }

        });

        sidePanel.add(save);
        save.addActionListener(e -> {

        });

        sidePanel.add(exit);
        exit.addActionListener(e -> System.exit(0));

        //Simulator
        sim.setBackground(lightGray);
        sim.addActionListener(e -> {
            sim.setBackground(new Color(204, 255, 229));
            city.setBackground(lightGray);
            newCity.setEnabled(false);
            newCity.setBackground(white);
            open.setEnabled(false);
            open.setBackground(white);
            save.setEnabled(false);
            save.setBackground(white);
            exit.setEnabled(false);
            exit.setBackground(white);
            loadMap.setEnabled(true);
            loadMap.setBackground(new Color(204, 255, 255));
            addVehicle.setEnabled(true);
            addVehicle.setBackground(new Color(204, 255, 255));
            start.setEnabled(true);
            start.setBackground(new Color(102, 255, 102));
            stop.setEnabled(true);
            stop.setBackground(new Color(255, 51, 51));
            update.setEnabled(true);
            update.setBackground(new Color(204, 255, 255));
        });
        topPanel.add(sim);
        sidePanel.add(loadMap);

        sidePanel.add(addVehicle);
        addVehicle.setEnabled(false);

        sidePanel.add(start);
        start.setEnabled(false);
        start.addActionListener(e -> {
            simulationPanel.simulate();
            statusLabel.setText("Status: Simulation Started");
            simulationPanel.setStopSim(false);
            mainWindow.validate();
            mainWindow.repaint();
        });

        addVehicle.addActionListener(e -> {
            String spawnInput = JOptionPane.showInputDialog("Total number of Vehicles to spawn:");
            int spawns = Integer.parseInt(spawnInput);
            simulationPanel.setVehicleSpawn(spawns);
            String spawnRateInput = JOptionPane.showInputDialog("Number of Simulation tics between spawns:");
            int spawnRate = Integer.parseInt(spawnRateInput);
            simulationPanel.setVehicleSpawnRate(spawnRate);
        });

        sidePanel.add(stop);
        stop.setEnabled(false);
        stop.addActionListener(e -> {
            simulationPanel.setStopSim(true);
            statusLabel.setText("Status: Simulation Stopped");
            mainWindow.validate();
            mainWindow.repaint();
        });

        loadMap.addActionListener(e -> {
            statusLabel.setText("Status: Map Loaded!");
            editorPanel.setVisible(false);
            simulationPanel = new SimulationPanel();
            simulationPanel.setScale(SCALE);
            simulationPanel.loadMap(editorPanel.getRoads(), editorPanel.getLights());
            mainWindow.add(simulationPanel);
            start.setEnabled(true);
            addVehicle.setEnabled(true);
            stop.setEnabled(true);
            mainWindow.repaint();
        });

        sidePanel.add(update);
        update.addActionListener(e -> {
            String updateRateInput = JOptionPane.showInputDialog("Enter the Update Rate of the Simulation");
            int updateRate = Integer.parseInt(updateRateInput);
            simulationPanel.setUpdateRate(updateRate);
            statusLabel.setText("Status: Update Rate set to " + updateRate);
            mainWindow.validate();
            mainWindow.repaint();
        });

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

    }
}

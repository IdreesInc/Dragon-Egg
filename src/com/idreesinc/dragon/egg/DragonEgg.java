package com.idreesinc.dragon.egg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DragonEgg {

    private static final String SEPERATOR = File.separator;
    private static final int MAX_DISTANCE = 15;

    public static void main(String[] args) {
        String path = DragonEgg.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("file:", "").replace("%20", " ");
        Random rand = new Random();
        int folderLevel = path.length() - path.replace(SEPERATOR, "").length();
        int newFolderLevel = folderLevel;
        File newLocation = new File(System.getProperty("user.dir"));
        for (int y = 0; y < MAX_DISTANCE; y++) {
            if (rand.nextBoolean() && newFolderLevel > 3) {
                newLocation = newLocation.getParentFile();
                newFolderLevel--;
            } else {
                String subdirectory = getFolderInPath(newLocation.getAbsolutePath());
                if (subdirectory != null) {
                    newFolderLevel++;
                    newLocation = new File(subdirectory);
                }
            }
        }
        if (!newLocation.getPath().contains("Dragon_Egg.jar")) {
            newLocation = new File(newLocation.getAbsolutePath() + SEPERATOR + "Dragon_Egg.jar");
        }
        if (path.contains("jar")) {
            try {
                Files.move(Paths.get(path), newLocation.toPath(), REPLACE_EXISTING);
                say("*Poof*" + "\nPssst: " + newLocation.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(DragonEgg.class.getName()).log(Level.SEVERE, null, ex);
                say("Error: " + ex.toString());
            }
        }
    }

    public static String getFolderInPath(String path) {
        File file = new File(path);
        String[] names = file.list();
        ArrayList<String> folders = new ArrayList<>();
        for (String name : names) {
            if (new File(path + SEPERATOR + name).isDirectory() && !name.contains("%")) {
                folders.add(path + SEPERATOR + name);
            }
        }
        if (!folders.isEmpty()) {
            Random rand = new Random();
            return folders.get(rand.nextInt(folders.size()));
        } else {
            return null;
        }
    }

    private static void say(String message) {
        JOptionPane.showMessageDialog(null, message, "Dragon Egg", JOptionPane.INFORMATION_MESSAGE);
    }
}

package com.idreesinc.dragon.egg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DragonEgg {

    public static void main(String[] args) {
        String path = DragonEgg.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("file:", "").replace("%20", " ");
        if (path.contains("jar")) {
            try {
                Files.move(Paths.get(path), Paths.get(path.replace("Dragon_Egg.jar", "Banana/Dragon_Egg.jar")), REPLACE_EXISTING);
                say("Poof");
            } catch (IOException ex) {
                Logger.getLogger(DragonEgg.class.getName()).log(Level.SEVERE, null, ex);
                say("Error: " + ex.toString());
            }
        }
    }

    private static void say(String message) {
        JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.INFORMATION_MESSAGE);
    }
}

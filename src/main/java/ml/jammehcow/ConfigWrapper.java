package ml.jammehcow;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Author: jammehcow.
 * Date: 21/12/16.
 */

public class ConfigWrapper {
    public static Yaml loadConfig() {
        // Will return Yaml later on
        Yaml yaml = new Yaml();

        try {
            // TODO: Improve the fuck out of this. Suggestions?
            File cfgFile = new File((new File(System.getProperty("java.class.path"))).getAbsoluteFile().getParentFile() + "config.yml");

            if (!cfgFile.exists()) {
                InputStream resource = Main.class.getClassLoader().getResourceAsStream("ml/jammehcow/config.yml");

                if (resource == null)
                    throw new FileNotFoundException("config.yml not found. What've you done Timmy?!");
            }

            //InputStream input = new FileInputStream();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return yaml;
    }
}

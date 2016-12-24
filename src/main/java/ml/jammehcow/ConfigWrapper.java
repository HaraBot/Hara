package ml.jammehcow;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;

/**
 * Author: jammehcow.
 * Date: 21/12/16.
 */

public class ConfigWrapper {
    private final int CURRENT_REV = 1;

    public static Config getConfig() {
        @SuppressWarnings("unchecked")
        Config results = null;

        try {
            File cfgFile = new File((new File(System.getProperty("java.class.path"))).getAbsoluteFile().getParentFile() + File.separator +"config.yml");

            if (!cfgFile.exists()) {
                InputStream resource = Main.class.getClassLoader().getResourceAsStream("ml/jammehcow/config.yml");

                if (resource == null) throw new FileNotFoundException("config.yml not found. What've you done Timmy?!");
            }

            YamlReader reader = new YamlReader(new FileReader("config.yml"));
            Config object = reader.read(Config.class);
            results = object;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (YamlException e) {
            e.printStackTrace();
        }

        return results;
    }
}

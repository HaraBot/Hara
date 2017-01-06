package ml.jammehcow.Config;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import ml.jammehcow.Main;

import java.io.*;

import static ml.jammehcow.Main.logger;

/**
 * Author: jammehcow.
 * Date: 21/12/16.
 */

public class ConfigWrapper {
    private static final int CURRENT_REV = 1;

    public static Config getConfig() {
        Config results = null;

        try {
            File cfgFile = new File((new File(System.getProperty("java.class.path"))).getAbsoluteFile().getParentFile() + File.separator +"config.yml");
            logger.info(cfgFile.toString());

            if (!cfgFile.exists()) {
                InputStream resource = Main.class.getClassLoader().getResourceAsStream("ml/jammehcow/config.yml");

                if (resource == null) {
                    try {
                        exportResource("config.yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // TODO: fix this
            YamlReader reader = new YamlReader(new FileReader("config.yml"));
            Config object = reader.read(Config.class);
            results = object;

        } catch (FileNotFoundException | YamlException e) {
            e.printStackTrace();
        }

        return results;
    }

    static String exportResource(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = Main.class.getResourceAsStream(resourceName);
            if(stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(Config.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(jarFolder + File.separator + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            stream.close();
            resStreamOut.close();
        }

        return jarFolder + resourceName;
    }
}

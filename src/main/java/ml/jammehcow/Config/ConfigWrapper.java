package ml.jammehcow.Config;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import ml.jammehcow.Main;

import java.io.*;

/**
 * Author: jammehcow.
 * Date: 21/12/16.
 */

public class ConfigWrapper {
    private static final int CURRENT_REV = 2;

    public static Config getConfig() {
        Config results = null;

        try {
            // Surely this could look better!
            File cfgFile = new File((new File(System.getProperty("java.class.path"))).getAbsoluteFile().getParentFile() + File.separator + "config.yml");

            if (cfgFile.exists()) {
                YamlReader reader = new YamlReader(new InputStreamReader(new FileInputStream(cfgFile), "UTF-8"));
                results = reader.read(Config.class);
            } else {
                InputStream resource = Main.class.getClassLoader().getResourceAsStream("ml/jammehcow/config.yml");

                if (resource != null) exportConfig();
            }
        } catch (YamlException | FileNotFoundException | UnsupportedEncodingException e) {
            // Shouldn't reach a FileNotFoundException
            e.printStackTrace();
        }

        return results;
    }

    private static void exportConfig() {
        InputStream stream = null;
        OutputStream resStreamOut = null;

        try {
            stream = Main.class.getResourceAsStream("config.yml");
            if (stream == null) throw new Exception("Cannot get resource \"" + "config.yml" + "\" from Jar file.");

            int readBytes;
            byte[] buffer = new byte[4096];

            String jarFolder = new File(Config.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(jarFolder + File.separator + "config.yml");

            while ((readBytes = stream.read(buffer)) > 0) resStreamOut.write(buffer, 0, readBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stream != null) stream.close();
                if (resStreamOut != null) resStreamOut.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}

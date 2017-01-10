package ml.jammehcow.LuaEnvironment.PluginWrapper;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import static ml.jammehcow.LuaEnvironment.PluginWrapper.Plugin.loadedPlugins;
import static ml.jammehcow.Main.logger;

/**
 * Author: jammehcow.
 * Date: 4/01/17.
 */

public class PluginLoader {
    private static void loadPlugin(File config, File main) {
        try {
            YamlReader c = new YamlReader(new FileReader(config));
            Map configMap = (Map)c.read();

            // TODO: check that config is complete.

            String name = (String)configMap.get("name");

            loadedPlugins.add(new Plugin(name, main, configMap));
            logger.info("Loaded plugin " + name);
        } catch (FileNotFoundException | YamlException e) {
            e.printStackTrace();
        }
    }

    public static void loadAllPlugins() {
        ArrayList<File> available = PluginHandler.getAvailablePlugins();
        if (!available.isEmpty()) {
            for (File c : available) {
                File f = new File(c.getParentFile().getAbsolutePath() + File.separator + "conf.yml");
                loadPlugin(c, f);
            }
        } else {
            logger.warn("There are no plugins installed in the plugins/ folder. This bot has no functionality now, apart from being online.");
        }

        PluginHandler.enableAll();
    }
}

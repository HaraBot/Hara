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

            if (!checkConfig(configMap)) {
                throw new YamlException("Your conf.yml is missing some keys. Have a look at the example https://github.com/jammehcow/Hara-Example-Plugin/blob/master/Example-Plugin/conf.yml and see what you're missing from the \"Required keys\" section.");
            } else {
                String name = (String)configMap.get("name");

                loadedPlugins.add(new Plugin(name, main, configMap));
                logger.info("Loaded plugin " + name);
            }
        } catch (FileNotFoundException | YamlException e) {
            e.printStackTrace();
        }
    }

    public static void loadAllPlugins() {
        ArrayList<File> available = PluginHandler.getAvailablePlugins();
        if (!available.isEmpty()) {
            for (File c : available) {
                File f = new File(c.getParentFile().getAbsolutePath() + File.separator + "main.lua");
                loadPlugin(c, f);
            }
        } else {
            logger.warn("There are no plugins installed in the plugins/ folder. This bot has no functionality now, apart from being online.");
        }

        PluginHandler.enableAll();
    }

    private static boolean checkConfig(Map cfg) {
        return cfg.containsKey("name") && cfg.containsKey("author") && cfg.containsKey("description") && cfg.containsKey("version");
    }
}

package ml.jammehcow.LuaEnvironment.PluginWrapper;

import ml.jammehcow.Main;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static ml.jammehcow.LuaEnvironment.PluginWrapper.PluginLoader.getLoadedPlugins;
import static ml.jammehcow.Main.logger;

/**
 * Author: jammehcow.
 * Date: 4/01/17.
 */

public class PluginHandler {
    public static ArrayList<File> getAvailablePlugins() {
        ArrayList<File> pluginsReturned = new ArrayList<>();

        File pluginsDir = null;
        try {
            pluginsDir = new File(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile() + File.separator + "plugins");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (!pluginsDir.exists()) {
            pluginsDir.mkdir();
        } else {
            File[] dirs = pluginsDir.listFiles(File::isDirectory);
            if (dirs != null) {
                for (File f : dirs) {
                    File m =  new File(f.getAbsoluteFile() + File.separator + "main.lua");
                    File c =  new File(f.getAbsoluteFile() + File.separator + "conf.yml");
                    if (c.exists() && m.exists()) {
                        pluginsReturned.add(c);
                    } else {
                        logger.warn(f.getName() + " was found in your plugins folder, but doesn't contain (either) a main.lua or conf.yml. It won't be loaded until that's fixed.");
                    }
                }
            }
        }

        return pluginsReturned;
    }

    public static void reloadAllPlugins() {
        disableAll();
        logger.info("Reloading all plugins");
        PluginLoader.loadAllPlugins();
    }

    public static void enableAll() {
        for (Plugin p : getLoadedPlugins()) p.enable();
    }

    public static void disableAll() {
        for (Plugin p : getLoadedPlugins()) {
            // Disable each plugin in the list and remove it from the array.
            // Disabling is not like enabling. It removes it from memory, there is no setting self.enabled to false unless done single.
            p.disable();
            PluginLoader.removePlugin(p);
        }
    }
}

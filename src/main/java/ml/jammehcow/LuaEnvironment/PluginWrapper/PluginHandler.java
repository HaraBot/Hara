package ml.jammehcow.LuaEnvironment.PluginWrapper;

import java.io.File;
import java.util.ArrayList;

import static ml.jammehcow.LuaEnvironment.PluginWrapper.Plugin.loadedPlugins;
import static ml.jammehcow.Main.logger;

/**
 * Author: jammehcow.
 * Date: 4/01/17.
 */

public class PluginHandler {
    public static ArrayList<File> getAvailablePlugins() {
        ArrayList<File> pluginsReturned = new ArrayList<>();

        File pluginsDir = new File((new File(System.getProperty("java.class.path"))).getAbsoluteFile().getParentFile() + File.separator + "plugins");

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
                        logger.warn(f.getName() + " was found in your plugins folder, but doesn't contain (either) a main.lua or conf.yml. It isn't be loaded until that's fixed.");
                    }
                }
            }
        }

        return pluginsReturned;
    }

    public static void callPlugin(Plugin plugin) {
        if (plugin.isEnabled()) plugin.getChunk().call();
    }

    public static void reloadAllPlugins() {
        disableAll();
        logger.info("Reloading all plugins");
        PluginLoader.loadAllPlugins();
    }

    public static void enableAll() {
        for (Plugin p : loadedPlugins) p.enable();
    }

    public static void disableAll() {
        for (Plugin p : loadedPlugins) {
            // Disable each plugin in the list and remove it from the array.
            // Disabling is not like enabling. It removes it from memory, there is no setting self.enabled to false unless done single.
            p.disable();
            loadedPlugins.remove(p);
        }

        // Ensure that the whole ArrayList is cleared.
        loadedPlugins.clear();
    }
}

package nz.co.jammehcow.LuaEnvironment.PluginWrapper;

import nz.co.jammehcow.Handlers.EventHandlers;
import nz.co.jammehcow.Main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static nz.co.jammehcow.Main.logger;

/**
 * @author jammehcow
 */

public class PluginHandler {
    public static ArrayList<File> getAvailablePlugins() {
        ArrayList<File> pluginsReturned = new ArrayList<>();

        File pluginsDir = null;
        try {
            pluginsDir = new File(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile() + File.separator + "plugins");

            if (!pluginsDir.exists()) {
                if (!pluginsDir.mkdir()) {
                    throw new IOException("There was a problem creating the plugins directory. There shouldn't be an error here so please send this to the developer with the stacktrace and system specs.");
                }
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
        } catch (URISyntaxException | IOException e) { e.printStackTrace(); }

        return pluginsReturned;
    }

    public static void reloadAllPlugins() {
        disableAll();
        logger.info("Reloading all plugins");
        EventHandlers.registeredEvents.clear();
        EventHandlers.events.forEach((e) -> EventHandlers.registeredEvents.put(e.getSimpleName(), new ArrayList<>()));
        PluginLoader.loadAllPlugins();
    }

    public static void enableAll() { for (Plugin p : PluginLoader.getLoadedPlugins()) p.enable(); }

    public static void disableAll() {
        for (Plugin p : PluginLoader.getLoadedPlugins()) {
            // Disable each plugin in the list and remove it from the array.
            // Disabling is not like enabling. It removes it from memory, there is no setting self.enabled to false.
            p.disable();
        }

        PluginLoader.removeAllPlugins();
    }
}

package ml.jammehcow.Handlers;

import ml.jammehcow.LuaEnvironment.PluginWrapper.Plugin;
import ml.jammehcow.LuaEnvironment.PluginWrapper.PluginCommand;
import ml.jammehcow.LuaEnvironment.PluginWrapper.PluginLoader;
import ml.jammehcow.Main;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Author: jammehcow.
 * Date: 24/12/16.
 */

public class CommandPluginHandler {
    static boolean parseCommand(IMessage m) {
        for (Plugin p : PluginLoader.getLoadedPlugins()) {
            for (PluginCommand c : p.getCommands()) {
                if (m.getContent().startsWith(Main.prefix + c.getCommand())) {
                    c.callCommand(m);
                    return true;
                }
            }
        }

        return false;
    }
}

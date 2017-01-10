package ml.jammehcow.LuaEnvironment.PluginWrapper;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import sx.blah.discord.handle.impl.obj.Message;

/**
 * Author: jammehcow.
 * Date: 6/01/17.
 */

public class PluginCommand {
    private String cmd;
    private String usage;
    private LuaValue cb;
    private Plugin plugin;

    public PluginCommand(Plugin plugin, String command, String usage, LuaFunction cb) {
        this.plugin = plugin;
        this.cmd = command;
        this.usage = usage;
        this.cb = cb;
    }

    public void call(Message m, String[] args) {
        // Stub
    }
}

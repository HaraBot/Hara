package ml.jammehcow.LuaEnvironment.Plugin;

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

    public PluginCommand(String command, String usage, LuaFunction cb) {
        this.cmd = command;
        this.usage = usage;
        this.cb = cb;
    }

    public void call(Message m, String[] args) {
        // Stub
    }
}

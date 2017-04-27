package ml.jammehcow.LuaEnvironment.PluginWrapper;

import ml.jammehcow.Main;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import sx.blah.discord.handle.obj.IMessage;

/**
 * @author jammehcow
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

    public String getCommand() { return this.cmd; }
    public String getUsage() { return this.usage; }

    public void callCommand(IMessage m) {
        String[] args = m.getContent().replace(Main.prefix + this.cmd, "").trim().split(" ");
        LuaTable argsTable = new LuaTable();

        for (String arg : args) {
            argsTable.insert(0, LuaString.valueOf(arg));
        }

        this.cb.call(CoerceJavaToLua.coerce(m), argsTable);
    }
}

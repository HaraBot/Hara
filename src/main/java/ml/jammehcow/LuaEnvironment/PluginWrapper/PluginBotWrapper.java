package ml.jammehcow.LuaEnvironment.PluginWrapper;

import ml.jammehcow.Main;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

/**
 * Author: jammehcow.
 * Date: 6/01/17.
 */

class PluginBotWrapper extends LuaTable {
    PluginBotWrapper(Plugin plugin) {
        set("log", new PluginLoggingWrapper());

        set("registerCommand", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                plugin.addCmd(new PluginCommand(plugin, args.tojstring(1), args.tojstring(2), args.checkfunction(3)));
                return LuaValue.NIL;
            }
        });

        set("pluginEnable", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                plugin.setEnableCB(args.checkfunction(1));
                return LuaValue.NIL;
            }
        });

        set("pluginDisable", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                plugin.setDisableCB(args.checkfunction(1));
                return LuaValue.NIL;
            }
        });

        set("prefix", LuaValue.valueOf(Main.prefix));
    }
}

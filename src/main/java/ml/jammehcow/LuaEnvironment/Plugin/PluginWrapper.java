package ml.jammehcow.LuaEnvironment.Plugin;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

import static ml.jammehcow.Main.logger;

/**
 * Author: jammehcow.
 * Date: 6/01/17.
 */

public class PluginWrapper extends LuaTable {
    public PluginWrapper(Plugin plugin) {
        set("registerCommand", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                PluginHandler.registerCommand(plugin, new PluginCommand(args.tojstring(1), args.tojstring(2), args.checkfunction(4)));
                return LuaValue.NIL;
            }
        });

        // TODO: I know this is messy, I'll change to a table with something like "bot.log.warn()"
        set("log", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                if (args.tojstring(1).equals("info")) {
                    logger.info(args.tojstring(2));
                } else if (args.tojstring(1).equals("warn")) {
                    logger.warn(args.tojstring(2));
                } else if (args.tojstring(1).equals("error")) {
                    logger.error(args.tojstring(2));
                } else if (args.tojstring(1).equals("debug")) {
                    logger.debug(args.tojstring(2));
                }
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
    }
}

package ml.jammehcow.LuaEnvironment.PluginWrapper;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

import static ml.jammehcow.Main.logger;

/**
 * Author: jammehcow.
 * Date: 6/01/17.
 */

public class PluginBotWrapper extends LuaTable {
    public PluginBotWrapper(Plugin plugin) {
        set("registerCommand", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                plugin.addCmd(new PluginCommand(plugin, args.tojstring(1), args.tojstring(2), args.checkfunction(4)));
                return LuaValue.NIL;
            }
        });

        // TODO: I know this is messy, I'll change to a table with something like "bot.log.warn()"
        set("log", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                switch (args.tojstring(1).toLowerCase()) {
                    case "info":
                        logger.info(args.tojstring(2));
                        break;
                    case "warn":
                        logger.warn(args.tojstring(2));
                        break;
                    case "error":
                        logger.error(args.tojstring(2));
                        break;
                    case "debug":
                        logger.debug(args.tojstring(2));
                        break;
                    default:
                        break;
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

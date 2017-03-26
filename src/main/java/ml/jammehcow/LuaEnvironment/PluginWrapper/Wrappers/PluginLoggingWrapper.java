package ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers;

import ml.jammehcow.LuaEnvironment.PluginWrapper.Plugin;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: jammehcow.
 * Date: 11/01/17.
 */

public class PluginLoggingWrapper extends LuaTable {
    private static Logger logger = LoggerFactory.getLogger(PluginLoggingWrapper.class);

    public PluginLoggingWrapper(Plugin plugin) {
        String prefix = "[plugin-" + plugin.getName() + "] ";

        set("info", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                logger.info(prefix + args.tojstring(1));
                return LuaValue.NIL;
            }
        });

        set("warn", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                logger.warn(prefix + args.tojstring(1));
                return LuaValue.NIL;
            }
        });

        set("error", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                logger.error(prefix + args.tojstring(1));
                return LuaValue.NIL;
            }
        });

        set("debug", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                logger.debug(prefix + args.tojstring(1));
                return LuaValue.NIL;
            }
        });
    }
}

package ml.jammehcow.LuaEnvironment.PluginWrapper;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

import static ml.jammehcow.Main.logger;

/**
 * Author: jammehcow.
 * Date: 11/01/17.
 */

class PluginLoggingWrapper extends LuaTable {
    PluginLoggingWrapper() {
        set("info", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                logger.info(args.tojstring(1));
                return LuaValue.NIL;
            }
        });

        set("warn", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                logger.warn(args.tojstring(1));
                return LuaValue.NIL;
            }
        });

        set("error", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                logger.error(args.tojstring(1));
                return LuaValue.NIL;
            }
        });

        set("debug", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                logger.debug(args.tojstring(1));
                return LuaValue.NIL;
            }
        });
    }
}

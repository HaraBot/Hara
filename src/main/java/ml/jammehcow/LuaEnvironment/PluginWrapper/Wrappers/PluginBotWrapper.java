package ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers;

import ml.jammehcow.Main;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RateLimitException;

/**
 * Author: jammehcow.
 * Date: 26/03/17.
 */

public class PluginBotWrapper extends LuaTable {
    public PluginBotWrapper() {
        set("prefix", LuaValue.valueOf(Main.prefix));

        set("getClient", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                return CoerceJavaToLua.coerce(Main.client);
            }
        });

        set("requestBuffer", new VarArgFunction() {
            @Override
            public synchronized Varargs invoke(Varargs args) {
                try {
                    args.checkfunction(1).call();
                } catch (LuaError e) {
                    if (e.getCause() instanceof RateLimitException) {
                        try {
                            long waitPeriod = ((RateLimitException) e.getCause()).getRetryDelay();
                            wait(waitPeriod);
                            args.checkfunction(1).call();
                        } catch (Exception e1) { e1.printStackTrace(); }
                    }
                }
                return LuaValue.NIL;
            }
        });

        set("createEmbed", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                return CoerceJavaToLua.coerce(new EmbedBuilder());
            }
        });
    }
}

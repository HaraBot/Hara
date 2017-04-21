package ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers;

import ml.jammehcow.Main;
import org.luaj.vm2.*;
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
                return sendRequest(args.checkfunction(1));
            }
        });

        set("createEmbed", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                return CoerceJavaToLua.coerce(new EmbedBuilder());
            }
        });
    }

    private synchronized LuaValue sendRequest(LuaFunction func) {
        try { func.call(); }
        catch (LuaError e) {
            if (e.getCause() instanceof RateLimitException) {
                try {
                    long waitPeriod = ((RateLimitException) e.getCause()).getRetryDelay();
                    wait(waitPeriod);
                    // Call function recursively. At some point there'll be an opening.
                    return sendRequest(func);
                } catch (Exception e1) { e1.printStackTrace(); }
            }
        }
        return LuaValue.NIL;
    }
}

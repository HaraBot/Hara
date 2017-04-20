package ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers;

import ml.jammehcow.Main;
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
            public Varargs invoke(Varargs args) {
                try {
                    args.checkfunction(1).call();
                } catch (RateLimitException e) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    args.checkfunction(1).call();
                                }
                            },
                            e.getRetryDelay()
                    );
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

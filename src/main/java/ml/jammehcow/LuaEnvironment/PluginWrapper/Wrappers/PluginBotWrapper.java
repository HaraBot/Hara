package ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers;

import ml.jammehcow.Main;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import sx.blah.discord.util.RequestBuffer;

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
                RequestBuffer.request(() -> args.checkfunction(1).call());
                return LuaValue.NIL;
            }
        });
    }
}

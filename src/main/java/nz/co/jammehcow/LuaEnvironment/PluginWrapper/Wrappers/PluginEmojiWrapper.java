package nz.co.jammehcow.LuaEnvironment.PluginWrapper.Wrappers;

import com.vdurmont.emoji.EmojiParser;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

/**
 * @author jammehcow
 */

public class PluginEmojiWrapper extends LuaTable {
    public PluginEmojiWrapper() {
        set("getAlias", new VarArgFunction() {
            // Returns alias (string) version of emoji (like :thumbsup:)
            @Override
            public Varargs invoke(Varargs args) {
                return (!args.tojstring(1).isEmpty()) ? LuaValue.valueOf(EmojiParser.parseToAliases(args.tojstring(1))) : LuaValue.NIL;
            }
        });

        set("getEmoji", new VarArgFunction() {
            // Returns the unicode emoji from an alias
            @Override
            public Varargs invoke(Varargs args) {
                return (!args.tojstring(1).isEmpty()) ? LuaValue.valueOf(EmojiParser.parseToAliases(args.tojstring(1))) : LuaValue.NIL;
            }
        });
    }
}

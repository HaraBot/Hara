package ml.jammehcow.LuaEnvironment;

import ml.jammehcow.LuaEnvironment.Plugin.PluginLoader;
import ml.jammehcow.Main;
import org.luaj.vm2.Globals;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Author: jammehcow.
 * Date: 6/01/17.
 */

public class LuaEnvironment {
    static Globals globals;

    public static void loadEnv() {
        globals = (Main.debug) ? JsePlatform.debugGlobals() : JsePlatform.standardGlobals();

        LuaC.install(globals);
        globals.compiler = LuaC.instance;

        PluginLoader.loadAllPlugins();
    }
}

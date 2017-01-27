package ml.jammehcow.LuaEnvironment;

import ml.jammehcow.LuaEnvironment.PluginWrapper.PluginLoader;
import ml.jammehcow.Main;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Author: jammehcow.
 * Date: 6/01/17.
 */

public class LuaEnvironment {
    public static Globals globals;

    public static void init() {
        PluginLoader.loadAllPlugins();
    }

    public static Globals getEnv() {
        return (Main.debug) ? JsePlatform.debugGlobals() : JsePlatform.standardGlobals();
    }
}

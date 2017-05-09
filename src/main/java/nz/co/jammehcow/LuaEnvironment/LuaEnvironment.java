package nz.co.jammehcow.LuaEnvironment;

import nz.co.jammehcow.LuaEnvironment.PluginWrapper.PluginLoader;
import nz.co.jammehcow.Main;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * @author jammehcow
 */

public class LuaEnvironment {
    public static void init() { PluginLoader.loadAllPlugins(); }

    public static Globals getEnv() {
        return (Main.debug) ? JsePlatform.debugGlobals() : JsePlatform.standardGlobals();
    }
}

package ml.jammehcow.LuaEnvironment.PluginWrapper;

import ml.jammehcow.LuaEnvironment.LuaEnvironment;
import ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers.PluginBotWrapper;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * Author: jammehcow.
 * Date: 4/01/17.
 */

public class Plugin {
    private String name;
    private PluginDescriptor description;
    private File plugin;
    private File pluginFolder;
    private Map config;
    private LuaValue chunk;
    private boolean enabled;
    private ArrayList<PluginCommand> commands = new ArrayList<>();
    private LuaFunction enableCB;
    private LuaFunction disableCB;

    private Globals globals;

    public Plugin(String name, File file, Map config) {
        this.name = name;
        this.plugin = file;
        this.enabled = false;

        this.globals = LuaEnvironment.globals;
        this.pluginFolder = file.getParentFile().getAbsoluteFile();

        this.config = config;
        this.description = new PluginDescriptor(this.name, (String)this.config.get("version"), (String)this.config.get("description"), (String)this.config.get("author"));

        this.globals.set("bot", new PluginBotWrapper(this));
        this.chunk = this.globals.loadfile(file.getAbsolutePath());
    }

    public PluginDescriptor getDescription() { return this.description; }

    public LuaValue getChunk() { return this.chunk; }

    void disable() {
        this.enabled = false;
        this.disableCB.call(CoerceJavaToLua.coerce(this));
    }

    void enable() {
        this.enabled = true;
        this.chunk.call();
        this.enableCB.call(CoerceJavaToLua.coerce(this));
    }

    public void setEnableCB(LuaFunction cb) { this.enableCB = cb; }

    public void setDisableCB(LuaFunction cb) { this.disableCB = cb; }

    public boolean isEnabled() { return this.enabled; }

    public void addCmd(PluginCommand cmd) { commands.add(cmd); }

    public ArrayList<PluginCommand> getCommands() { return commands; }

    public String getName() { return this.name; }
}

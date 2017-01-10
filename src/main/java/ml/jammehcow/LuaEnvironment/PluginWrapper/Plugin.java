package ml.jammehcow.LuaEnvironment.PluginWrapper;

import ml.jammehcow.LuaEnvironment.LuaEnvironment;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: jammehcow.
 * Date: 4/01/17.
 */

public class Plugin {
    static ArrayList<Plugin> loadedPlugins = new ArrayList<>();
    static HashMap<Plugin, ArrayList<PluginCommand>> loadedCommands = new HashMap<>();

    private String name;
    private PluginDescriptor description;
    private File plugin;
    private Map config;
    private LuaValue chunk;
    private boolean enabled;
    private ArrayList<PluginCommand> commands = new ArrayList<>();
    private File pluginFolder;
    private PluginBotWrapper wrapper;
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

        loadedPlugins.add(this);
    }

    public PluginDescriptor getDescription() { return this.description; }

    public LuaValue getChunk() { return this.chunk; }

    public void disable() { this.enabled = false; }

    public void enable() {
        this.wrapper = new PluginBotWrapper(this);
        this.enabled = true;

        PluginHandler.callPlugin(this);
    }

    public void setEnableCB(LuaFunction cb) { this.enableCB = cb; }

    public void setDisableCB(LuaFunction cb) { this.disableCB = cb; }

    public boolean isEnabled() { return this.enabled; }

    public void addCmd(PluginCommand cmd) { commands.add(cmd); }

    public ArrayList<PluginCommand> getCommands() { return commands; }
}

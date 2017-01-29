package ml.jammehcow.LuaEnvironment.PluginWrapper;

import ml.jammehcow.LuaEnvironment.LuaEnvironment;
import ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers.PluginBotWrapper;
import ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers.PluginEmojiWrapper;
import ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers.PluginEventWrapper;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static ml.jammehcow.Main.logger;

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

    public Globals globals;

    public Plugin(String name, File file, Map config) {
        this.name = name;
        this.plugin = file;
        this.enabled = false;

        // Globals need to be set on a plugin to plugin basis. Resolves #1
        this.globals = LuaEnvironment.getEnv();
        this.pluginFolder = file.getParentFile().getAbsoluteFile();

        this.config = config;
        this.description = new PluginDescriptor(this.name, (String)this.config.get("version"), (String)this.config.get("description"), (String)this.config.get("author"));

        this.globals.set("bot", new PluginBotWrapper(this));
        this.globals.set("events", new PluginEventWrapper());
        this.globals.set("emoji", new PluginEmojiWrapper());
        this.chunk = this.globals.loadfile(file.getAbsolutePath());

        this.chunk.call();
    }

    public PluginDescriptor getDescription() { return this.description; }

    public LuaValue getChunk() { return this.chunk; }

    void disable() {
        this.enabled = false;
        if (this.disableCB != null) this.disableCB.call(CoerceJavaToLua.coerce(this));
    }

    void enable() {
        this.enabled = true;
        if (this.enableCB != null) this.enableCB.call(CoerceJavaToLua.coerce(this));
    }

    public void setEnableCB(LuaFunction cb) { this.enableCB = cb; }

    public void setDisableCB(LuaFunction cb) { this.disableCB = cb; }

    public boolean isEnabled() { return this.enabled; }

    public void addCmd(PluginCommand cmd) {
        for (Plugin p : PluginLoader.getLoadedPlugins()) {
            for (PluginCommand c : p.getCommands()) {
                if (c.getCommand().equals(cmd.getCommand())) {
                    logger.warn("Plugin " + this.getName() + " is requesting to register command " + cmd.getCommand() +
                                ", but the plugin " + p.getName() + " owns it. ");
                    return;
                }
            }
        }
        commands.add(cmd);
    }

    public ArrayList<PluginCommand> getCommands() { return commands; }

    public String getName() { return this.name; }
}

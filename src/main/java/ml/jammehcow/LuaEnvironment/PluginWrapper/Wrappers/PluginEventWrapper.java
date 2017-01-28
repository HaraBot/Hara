package ml.jammehcow.LuaEnvironment.PluginWrapper.Wrappers;

import org.luaj.vm2.LuaTable;

import static ml.jammehcow.Handlers.EventHandlers.events;

/**
 * Author: jammehcow.
 * Date: 27/01/17.
 */

public class PluginEventWrapper extends LuaTable {
    public PluginEventWrapper() {
        // Iterate through the defined events and bind a LuaValue of the class name (String) to the event index
        events.forEach((e) -> set(e.getName().replace("sx.blah.discord.handle.impl.events.", ""), events.indexOf(e)));
    }
}

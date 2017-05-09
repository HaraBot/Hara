package nz.co.jammehcow.LuaEnvironment.PluginWrapper.Wrappers;

import org.luaj.vm2.LuaTable;

import static nz.co.jammehcow.Handlers.EventHandlers.events;

/**
 * @author jammehcow
 */

public class PluginEventWrapper extends LuaTable {
    public PluginEventWrapper() {
        // Iterate through the defined events and bind a LuaValue of the class name (String) to the event index
        events.forEach((e) -> set(e.getSimpleName(), events.indexOf(e)));
    }
}

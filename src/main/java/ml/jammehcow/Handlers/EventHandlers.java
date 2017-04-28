package ml.jammehcow.Handlers;

import ml.jammehcow.LuaEnvironment.LuaEnvironment;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author jammehcow
 */

public class EventHandlers {
    public static final HashMap<String, ArrayList<LuaFunction>> registeredEvents = new HashMap<>();
    public static final ArrayList<Class<? extends Event>> events = new ArrayList<>();

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        LuaEnvironment.init();
    }

    @EventSubscriber
    public void onEvent(Event event) {
        registeredEvents.get(event.getClass().getSimpleName()).forEach((l) -> l.call(CoerceJavaToLua.coerce(event)));
    }
}

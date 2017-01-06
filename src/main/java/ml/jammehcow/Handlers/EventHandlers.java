package ml.jammehcow.Handlers;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Author: jammehcow.
 * Date: 22/12/16.
 */

public class EventHandlers {
    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        // Do something amazing and bot-ish.
    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        // Listen for messages and test against commands.
    }
}

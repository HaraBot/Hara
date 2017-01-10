package ml.jammehcow.Handlers;

import ml.jammehcow.Main;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.internal.json.event.ReactionEventResponse;
import sx.blah.discord.api.internal.json.objects.ReactionEmojiObject;
import sx.blah.discord.api.internal.json.objects.ReactionUserObject;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.obj.Reaction;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static ml.jammehcow.Main.logger;

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
        IMessage m = event.getMessage();
        String content = m.getContent();

        if (content.startsWith(Main.prefix)) {
            if (content.startsWith(Main.prefix + "plugin ")) {
                String args = content.replace(Main.prefix + "plugin ", "");

                EmbedBuilder embed = new EmbedBuilder()
                        .withTitle("Plugin: " + args)
                        .withDescription("This plugin is pretty cool aye?!\nThis is a description of this plugin.")
                        .withAuthorIcon("https://github.com/jammehcow.png")
                        .withAuthorName("jammehcow").withColor(Color.RED)
                        .withFooterText("Hara made with \u2764 and \uD83D\uDD52 by James Upjohn")
                        .withAuthorUrl("https://github.com/jammehcow/")
                        .withTimestamp(LocalDateTime.now());

                // Dummy list
                ArrayList<String> commands = new ArrayList<>();
                commands.add("cool_command_1");
                commands.add("cool_command_2");
                commands.add("cool_command_3");

                for (String s : commands) {
                    embed.appendField(Main.prefix + "**" + s + "**", "This is a description of " + s, true);
                }

                m.getChannel().sendMessage("<@" + m.getAuthor().getID() + ">, here's that description you wanted!", embed.build(), false);
            } else if (content.startsWith(Main.prefix + "kys")) {
                Main.client.logout();
                System.exit(0);
            } else if (content.startsWith(Main.prefix + "clear")) {
                int msgCount = m.getChannel().getMessages().size();
                m.getChannel().getMessages().bulkDelete(m.getChannel().getMessages());
                m.getChannel().sendMessage((msgCount > 100) ? "You owe me for clearing " + Integer.toString(msgCount) + " messages. \nA beer or two sounds good." : "I've just cleared " + Integer.toString(msgCount) + " messages. How fun!");
            }
        }
    }

    @EventSubscriber
    public void onReactionAddEvent(ReactionAddEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        if (event.getReaction().toString().equals("\uD83D\uDC4D")) {
            event.getMessage().getChannel().sendMessage("<@" + event.getUser().getID() + ">, I'm glad you like it!");
        }
    }
}

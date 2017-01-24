package ml.jammehcow.Handlers;

import ml.jammehcow.LuaEnvironment.PluginWrapper.Plugin;
import ml.jammehcow.LuaEnvironment.PluginWrapper.PluginCommand;
import ml.jammehcow.LuaEnvironment.PluginWrapper.PluginDescriptor;
import ml.jammehcow.Main;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.awt.*;
import java.time.LocalDateTime;

import static ml.jammehcow.LuaEnvironment.PluginWrapper.PluginLoader.getLoadedPlugins;
import static ml.jammehcow.Main.client;

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
        String content = m.getContent().trim() + " ";

        if (content.startsWith(Main.prefix)) {
            if (content.startsWith(Main.prefix + "plugins ")) {
                EmbedBuilder embed = new EmbedBuilder()
                        .withFooterText("Hara made with \u2764 and \uD83D\uDD52 by James Upjohn")
                        .withTimestamp(LocalDateTime.now())
                        .withAuthorName(client.getOurUser().getName())
                        .withAuthorIcon(client.getOurUser().getAvatarURL())
                        .withColor(Color.GREEN);

                if (!getLoadedPlugins().isEmpty()) {
                    for (Plugin p : getLoadedPlugins()) {
                        embed.appendField("**" + p.getName() + "**", p.getDescription().getDescription(), true);
                    }
                } else {
                    embed.appendField("No plugins to show", "There are no plugins loaded or enabled on this bot", false);
                }

                m.getChannel().sendMessage("<@" + m.getAuthor().getID() + ">, here's a list of installed plugins.", embed.build(), false);
            } else if (content.startsWith(Main.prefix + "plugin ")) {
                String args = content.replace(Main.prefix + "plugin ", "").trim();

                Plugin pluginRef = null;
                for (Plugin p : getLoadedPlugins()) {
                    if (p.getName().equalsIgnoreCase(args)) {
                        pluginRef = p;
                        break;
                    }
                }

                if (pluginRef != null) {
                    EmbedBuilder embed = new EmbedBuilder()
                            .withTitle("Plugin: " + pluginRef.getName())
                            .withDescription(pluginRef.getDescription().getDescription())
                            .withFooterText("Hara made with \u2764 and \uD83D\uDD52 by James Upjohn")
                            .withTimestamp(LocalDateTime.now())
                            .withAuthorName(pluginRef.getDescription().getAuthor())
                            .withColor(Color.RED);

                    PluginDescriptor description = pluginRef.getDescription();

                    if (pluginRef.isEnabled()) embed.withColor(Color.GREEN);

                    if (description.getAuthor().startsWith("github/")) {
                        embed.withAuthorName(description.getAuthor().replace("github/", ""))
                                .withAuthorUrl("https://github.com/" + description.getAuthor().replace("github/", "") + "/")
                                .withAuthorIcon("https://github.com/" + description.getAuthor().replace("github/", "") + ".png");
                    }

                    if (!pluginRef.getCommands().isEmpty()) {
                        for (PluginCommand c : pluginRef.getCommands()) embed.appendField("``" + Main.prefix + c.getCommand() + "``", "**Usage:** " + c.getUsage(), true);
                    } else {
                        embed.appendField("There are no commands!", "Does this plugin have any commands?", false);
                    }

                    m.getChannel().sendMessage(null, embed.build(), false);
                } else {
                    m.getChannel().sendMessage("That's not a valid plugin name!");
                }
            } else if (content.startsWith(Main.prefix + "quit ")) {
                client.logout();
                System.exit(0);
            } else if (content.startsWith(Main.prefix + "clear ")) {
                int msgCount = m.getChannel().getMessages().size();
                m.getChannel().getMessages().bulkDelete(m.getChannel().getMessages());
                m.getChannel().sendMessage((msgCount > 100) ? "You owe me for clearing " + Integer.toString(msgCount) + " messages. \nA beer or two sounds good." : "I've just cleared " + Integer.toString(msgCount) + " messages. How fun!");
            } else {
                if (!CommandPluginHandler.parseCommand(m)) {
                    m.getChannel().sendMessage("<@" + m.getAuthor().getID() + ">, that's not a valid command!");
                }
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

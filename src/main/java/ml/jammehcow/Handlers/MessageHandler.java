package ml.jammehcow.Handlers;

import ml.jammehcow.LuaEnvironment.PluginWrapper.*;
import ml.jammehcow.Main;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.*;

import java.awt.*;
import java.util.Optional;

import static ml.jammehcow.Main.client;

/**
 * Author: jammehcow.
 * Date: 30/03/17.
 */

public class MessageHandler {
    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        IMessage m = event.getMessage();
        String content = m.getContent().trim();

        if (content.startsWith(Main.prefix)) {
            content = content.replace(Main.prefix, "");
            String command = content.split(" ")[0];

            if (command.equals("plugins")) {
                EmbedBuilder embed = new EmbedBuilder()
                        .withFooterText("Hara made with \u2764 and \uD83D\uDD52 by James Upjohn")
                        .withTimestamp(System.currentTimeMillis())
                        .withAuthorName(client.getOurUser().getName())
                        .withAuthorIcon(client.getOurUser().getAvatarURL())
                        .withColor(Color.GREEN);

                if (!PluginLoader.getLoadedPlugins().isEmpty()) {
                    for (Plugin p : PluginLoader.getLoadedPlugins()) {
                        embed.appendField("**" + p.getName() + "**", p.getDescription().getDescription(), true);
                    }
                } else {
                    embed.appendField("No plugins to show", "There are no plugins loaded or enabled on this bot", false);
                }

                RequestBuffer.request(() -> {
                    try {
                        m.getChannel().sendMessage("<@" + m.getAuthor().getID() + ">, here's a list of installed plugins.", embed.build(), false);
                    } catch (MissingPermissionsException | DiscordException e) { e.printStackTrace(); }
                });
            } else if (command.equals("plugin")) {
                String args = content.replace("plugin ", "").trim();

                Plugin pluginRef = null;
                for (Plugin p : PluginLoader.getLoadedPlugins()) {
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
                            .withTimestamp(System.currentTimeMillis())
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
                        for (PluginCommand c : pluginRef.getCommands())
                            embed.appendField("``" + Main.prefix + c.getCommand() + "``", "**Usage:** " + c.getUsage(), true);
                    } else {
                        embed.appendField("There are no commands!", "Does this plugin have any commands?", false);
                    }

                    RequestBuffer.request(() -> {
                        try {
                            m.getChannel().sendMessage(null, embed.build(), false);
                        } catch (MissingPermissionsException | DiscordException e) { e.printStackTrace(); }
                    });
                } else {
                    RequestBuffer.request(() -> {
                        try {
                            m.getChannel().sendMessage("That's not a valid plugin name!");
                        } catch (MissingPermissionsException | DiscordException e) { e.printStackTrace(); }
                    });
                }
            } else if (command.equals("quit")) {
                client.logout();
                System.exit(0);
            } /*else if (command.equals("play")) {
                Optional<String> resultQueue = Main.manager.queueYouTubeVid(content.replace(Main.prefix + "play ", "").trim().toLowerCase(), m.getGuild().getID());

                if (!resultQueue.isPresent())
                    Main.manager.joinForPlay(m);
                else {
                    // stub
                }
            }*/ else if (command.equals("reload")) {
                if (m.getAuthor().getPermissionsForGuild(m.getGuild()).contains(Permissions.ADMINISTRATOR)) {
                    // TODO: Check for role names "Bot commander"
                    PluginHandler.reloadAllPlugins();
                } else {
                    RequestBuffer.request(() -> m.getChannel().sendMessage(m.getAuthor().mention() + " you don't have permission to run that command!"));
                }
            } else if (command.equals("help")) {
                String helpMessage = "**Help for " + client.getOurUser().getDisplayName(m.getGuild()) + "**";

                RequestBuffer.request(() -> {
                    try {
                        m.getChannel().sendMessage(helpMessage);
                    } catch (MissingPermissionsException | DiscordException e) { e.printStackTrace(); }
                });
            } else {
                if (!parseCommand(m)) {
                    RequestBuffer.request(() -> {
                        try {
                            m.getChannel().sendMessage("<@" + m.getAuthor().getID() + ">, that's not a valid command!");
                        } catch (MissingPermissionsException | DiscordException e) { e.printStackTrace(); }
                    });
                }
            }
        }
    }

    private static boolean parseCommand(IMessage m) {
        for (Plugin p : PluginLoader.getLoadedPlugins()) {
            for (PluginCommand c : p.getCommands()) {
                if (m.getContent().startsWith(Main.prefix + c.getCommand())) {
                    c.callCommand(m);
                    return true;
                }
            }
        }

        return false;
    }
}

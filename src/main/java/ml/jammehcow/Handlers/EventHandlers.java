package ml.jammehcow.Handlers;

import ml.jammehcow.LuaEnvironment.LuaEnvironment;
import ml.jammehcow.LuaEnvironment.PluginWrapper.*;
import ml.jammehcow.Main;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import static ml.jammehcow.Main.client;

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

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        IMessage m = event.getMessage();
        String content = m.getContent().trim();

        if (content.startsWith(Main.prefix) && !content.matches(Pattern.quote(Main.prefix) + "{2,}+")) {
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
                        m.getChannel().sendMessage(m.getAuthor().mention() + ", here's a list of installed plugins.", embed.build(), false);
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
                if (m.getAuthor().getPermissionsForGuild(m.getGuild()).contains(Permissions.ADMINISTRATOR)) {
                    client.logout();
                    System.exit(0);
                } else {
                    m.getChannel().sendMessage("Sorry " + m.getAuthor().mention() + " but you don't have those privileges. Maybe quit Discord instead?");
                }
            } else if (command.equals("reload")) {
                if (isTrusted(m)) {
                    PluginHandler.reloadAllPlugins();
                    m.getChannel().sendMessage("Reloaded " + PluginLoader.getLoadedPlugins().size() + " plugins! Try " + Main.prefix + "plugins");
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
                            m.getChannel().sendMessage(m.getAuthor().mention() + ", that's not a valid command!");
                        } catch (MissingPermissionsException | DiscordException e) { e.printStackTrace(); }
                    });
                }
            }
        }
    }

    public static boolean isTrusted(IMessage m) {
        return (m.getAuthor().getPermissionsForGuild(m.getGuild()).contains(Permissions.ADMINISTRATOR) || m.getAuthor() == client.getApplicationOwner());
    }

    private static boolean parseCommand(IMessage m) {
        String command = m.getContent().split(" ")[0].replace(Main.prefix, "");
        for (Plugin p : PluginLoader.getLoadedPlugins()) {
            for (PluginCommand c : p.getCommands()) {
                if (command.equals(c.getCommand())) {
                    c.callCommand(m);
                    return true;
                }
            }
        }

        return false;
    }
}

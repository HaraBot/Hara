package ml.jammehcow;

import ml.jammehcow.Config.Config;
import ml.jammehcow.Handlers.EventHandlers;
import ml.jammehcow.LuaEnvironment.LuaEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

import java.util.Arrays;
import java.util.List;

import static ml.jammehcow.Config.ConfigWrapper.getConfig;

/**
 * Author: jammehcow.
 * Date: 21/12/16.
 */

public class Main {
    // Sets logger to SLF4J with logback
    public static final Logger logger  = LoggerFactory.getLogger(Main.class);
    private static Config config       = getConfig();
    private static final double REV    = 1.0;

    public static final String prefix = config.prefix;

    public static boolean debug = false;
    public static IDiscordClient client;


    public static void main(String[] args) throws DiscordException {
        logger.info("Starting Hara v" + REV);

        List<String> argsList = Arrays.asList(args);

        if (config.token.equals("your_discord_bot_token")) throw new DiscordException("Your bot token is the default token. You need to change the \"token\" field to your bot token in your config.yml");

        if (!argsList.contains("noclient")) {
            client = getClient();
            EventDispatcher dispatcher = client.getDispatcher();
            dispatcher.registerListener(new EventHandlers());
        }
        if (argsList.contains("debug")) { debug = true; }

        LuaEnvironment.init();
    }

    private static IDiscordClient getClient() throws DiscordException {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(config.token);

        return clientBuilder.login();
    }
}

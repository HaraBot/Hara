package ml.jammehcow;

import ml.jammehcow.Config.Config;
import ml.jammehcow.LuaEnvironment.Plugin.PluginLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
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

    private static final String prefix = config.prefix;

    public static boolean debug = false;


    public static void main(String[] args) throws DiscordException {
        logger.info("Starting Hara v" + REV);

        List<String> argsList = Arrays.asList(args);

        if (!argsList.contains("noclient")) { getClient(); }
        if (argsList.contains("debug")) { debug = true; }

        PluginLoader.loadAllPlugins();
    }

    private static IDiscordClient getClient() throws DiscordException {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(config.token);

        return clientBuilder.login();
    }
}

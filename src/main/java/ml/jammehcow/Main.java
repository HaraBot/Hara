package ml.jammehcow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.util.Map;

import static ml.jammehcow.ConfigWrapper.getConfig;

/**
 * Author: jammehcow.
 * Date: 21/12/16.
 */

public class Main {
    // Sets logger to SLF4J with logback
    public static final Logger logger  = LoggerFactory.getLogger(Main.class);
    // Grab that config!
    public static Config config        = getConfig();

    public static final double REV     = 1.0;

    // Will replace with config values
    private static final String prefix = config.prefix;


    public static void main(String[] args) {
        logger.info("Starting Hara v" + REV);
    }

    public static IDiscordClient getClient() throws DiscordException {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(config.token);

        return clientBuilder.login();
    }
}

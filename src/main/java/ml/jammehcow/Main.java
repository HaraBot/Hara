package ml.jammehcow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * Author: jammehcow.
 * Date: 21/12/16.
 */

public class Main {


    public static final Logger logger  = LoggerFactory.getLogger(Main.class);
    public static final double REV     = 1.0;

    // Will replace with config values
    private static final String token  = "bottoken";
    private static final String prefix = "!";

    public static void main(String[] args) {
        logger.info("Starting Hara v" + REV);
    }

    public static IDiscordClient getClient(String token) throws DiscordException {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);

        return clientBuilder.login();
    }
}

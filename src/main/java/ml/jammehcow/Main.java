package ml.jammehcow;

import org.yaml.snakeyaml.Yaml;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * Author: jammehcow.
 * Date: 21/12/16.
 */

public class Main {
    // Will replace with config values
    private static final String token  = "bottoken";
    private static final String prefix = "!";

    public static IDiscordClient getClient(String token) throws DiscordException {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        return clientBuilder.login();

    }
}

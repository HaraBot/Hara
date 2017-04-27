package ml.jammehcow.LuaEnvironment.PluginWrapper;

/**
 * @author jammehcow
 */

public class PluginDescriptor {
    private String name;
    private String version;
    private String description;
    private String author;

    public PluginDescriptor(String name, String version, String description, String author) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.author = author;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAuthor() {
        return this.author;
    }
}

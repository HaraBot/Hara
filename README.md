# Hara

[![Build Status](https://ci.jammehcow.ml/job/Hara/badge/icon)](https://ci.jammehcow.ml/job/Hara/)

Hara (the Japanese word for "original") is a small and highly extensible discord bot through the Lua scripting language. It's young, and therefore prone to crashes.
If something happens that wasn't supposed to, check to see if someone else has reported it as an issue or report it yourself with the log.

## Upcoming features...

- [ ] A functioning Lua API heh
- [ ] youtube-dl & audio support (with a Lua table like bot.ytdl.* to match)
- [ ] SQL support 
- [ ] Support for [all Discord4J events](https://jitpack.io/com/github/austinv11/Discord4j/2.7.0/javadoc/)
- [ ] Plugin dependencies

## Installation

Pre-requisites:
 - Java 8 or higher
 - A [Discord bot token](https://discordapp.com/developers/docs/topics/oauth2#registering-applications)

## Usage

Hara can be run using the following command (in the unzipped directory):

```
java -jar Hara.jar (optional args - see below)
```

#### Command line args

The example below shows an instance without a connection to Discord and logging debug messages (Java & Lua).
 
```
java -jar Hara.jar debug noclient
```

| Argument | Description |
| -------- | ----------- |
| debug    | Enables debug verbose |
| noclient | Stops the bot connecting to Discord |

## Lua plugins

One of Hara's biggest features is the included API for the Lua scripting language.

Rather than having to edit the source of Hara to add or edit features of your desire, you can include Lua scripts in the ```plugins/``` folder which directly interface with Hera's Lua API.
Hera itself doesn't have commands directly written into it. Instead plugins are bundled with the bot which can be removed at any time.

#### Examples

Primitive and likely to change example:
```lua
bot.enablePlugin(function()
    -- Called when this plugin is loaded.
    bot.log("info", bot:getDescription():getName() .. " has been enabled!")
end)

bot.disablePlugin(function()
    -- Called when the plugin is unloaded via shut down or internal disableAll() is called.
    bot.log("info", bot:getDescription():getName() .. " has been disabled! See you later.")
end)

bot.registerCommand("hello", "hello [name]", function(m, args)
    if args[1] ~= nil then
        -- <@name> is a mention; just as if you @ mentioned someone in chat.
        m.channel:sendMessage("Hello there <@" .. args[1] .. ">")
    else
        m.channel:sendMessage("Hello <@" .. m.author.name .. ">")
    end
end)
```
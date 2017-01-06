# Hara

Hara (the Japanese word for "original") is a small and highly extensible discord bot through the Lua scripting language. It's young, and therefore prone to crashes.
If something happens that wasn't supposed to, check to see if someone else has reported it as an issue or report it yourself with the log.

## Installation

Pre-requisites:
 - Java 8 or higher
 - Quite possibly ffmpeg

## Usage

Hara can be run using the following command (in the unzipped directory):

```
java -jar Hara.jar (optional args)
```

#### Command line args

| Argument | Description |
| -------- | ----------- |
| -v       | Enables debug verbose |
| noclient | Stops the bot connecting to Discord |

## Lua plugins

One of Hara's biggest features is the included API for the Lua scripting language.

Rather than having to edit the source of Hara to add or edit features of your desire, you can include Lua scripts in the ```plugins/``` folder which directly interface with Hera's Lua API.
Hera itself doesn't have commands directly written into it. Instead plugins are bundled with the bot which can be removed at any time.

#### Examples

Primitive and likely to change example:
```lua
bot.plugin(function(instance)
    instance.enablePlugin(function()
        bot.log("info", instance:getDescription():getName() .. " has been enabled!")
    end)
    
    instance.disablePlugin(function()
        bot.log("info", instance:getDescription():getName() .. " has been disabled! See you later.")
    end)
    
    instance.registerCommand("whois", "hello [name]", function(m, args)
        if args[1] == nil then
            -- <@name> is a mention; just as if you @ mentioned someone in chat.
            m.channel:sendMessage("Hello there <@" .. args[1] .. ">")
        else
            m.channel:sendMessage("Hello <@" .. m.author.name .. ">")
        end
    end)
end)
```
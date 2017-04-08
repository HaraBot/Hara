package ml.jammehcow.Handlers;

import ml.jammehcow.LuaEnvironment.LuaEnvironment;
import ml.jammehcow.LuaEnvironment.PluginWrapper.Plugin;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.*;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCreateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.TypingEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.*;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;
import sx.blah.discord.handle.impl.events.guild.channel.webhook.WebhookCreateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.webhook.WebhookDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.channel.webhook.WebhookUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.member.GuildMemberEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleCreateEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.voice.*;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserSpeakingEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelJoinEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelLeaveEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelMoveEvent;
import sx.blah.discord.handle.impl.events.shard.*;
import sx.blah.discord.handle.impl.events.user.PresenceUpdateEvent;
import sx.blah.discord.handle.impl.events.user.UserUpdateEvent;
import sx.blah.discord.util.audio.events.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: jammehcow.
 * Date: 22/12/16.
 */

public class EventHandlers {
    public static final HashMap<Class<? extends Event>, HashMap<Plugin, LuaFunction>> registeredEvents = new HashMap<>();
    public static final ArrayList<Class<? extends Event>> events = new ArrayList<>();

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        LuaEnvironment.init();

        /*try {
            Main.manager = new AudioManager();
        } catch (UnknownBindingException e) {
            e.printStackTrace();
        }*/
    }

    @EventSubscriber
    public void onAllUsersReceivedEvent(AllUsersReceivedEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onAudioPlayerCleanEvent(AudioPlayerCleanEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onAudioPlayerInitEvent(AudioPlayerInitEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onChannelCreateEvent(ChannelCreateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onChannelDeleteEvent(ChannelDeleteEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onChannelUpdateEvent(ChannelUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onDisconnectedEvent(DisconnectedEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onGuildCreateEvent(GuildCreateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onGuildEmojisUpdateEvent(GuildEmojisUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onGuildLeaveEvent(GuildLeaveEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onGuildMemberEvent(GuildMemberEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onGuildTransferOwnershipEvent(GuildTransferOwnershipEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onGuildUnavailableEvent(GuildUnavailableEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onGuildUpdateEvent(GuildUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onLoginEvent(LoginEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onLoopStateChangeEvent(LoopStateChangeEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onMentionEvent(MentionEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onMessageDeleteEvent(MessageDeleteEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onMessageEmbedEvent(MessageEmbedEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onMessagePinEvent(MessagePinEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onMessageSendEvent(MessageSendEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onMessageUnpinEvent(MessageUnpinEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onMessageUpdateEvent(MessageUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onPauseStateChangeEvent(PauseStateChangeEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onPresenceUpdateEvent(PresenceUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onProcessorAddEvent(ProcessorAddEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onProcessorRemoveEvent(ProcessorRemoveEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onReactionAddEvent(ReactionAddEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onReactionRemoveEvent(ReactionRemoveEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onReconnectFailureEvent(ReconnectFailureEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onReconnectSuccessEvent(ReconnectSuccessEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onResumedEvent(ResumedEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onRoleCreateEvent(RoleCreateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onRoleDeleteEvent(RoleDeleteEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onRoleUpdateEvent(RoleUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onShardReadyEvent(ShardReadyEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onShuffleEvent(ShuffleEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onTrackFinishEvent(TrackFinishEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onTrackQueueEvent(TrackQueueEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onTrackSkipEvent(TrackSkipEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onTrackStartEvent(TrackStartEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onTypingEvent(TypingEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onUserJoinEvent(UserJoinEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onUserSpeakingEvent(UserSpeakingEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onUserUpdateEvent(UserUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onUserVoiceChannelJoinEvent(UserVoiceChannelJoinEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onUserVoiceChannelLeaveEvent(UserVoiceChannelLeaveEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onUserVoiceChannelMoveEvent(UserVoiceChannelMoveEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onVoiceChannelCreateEvent(VoiceChannelCreateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onVoiceChannelDeleteEvent(VoiceChannelDeleteEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onVoiceChannelUpdateEvent(VoiceChannelUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onVoiceDisconnectedEvent(VoiceDisconnectedEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onVoicePingEvent(VoicePingEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onVolumeChangeEvent(VolumeChangeEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onWebhookCreateEvent(WebhookCreateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onWebhookDeleteEvent(WebhookDeleteEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

    @EventSubscriber
    public void onWebhookUpdateEvent(WebhookUpdateEvent event) {
        if (registeredEvents.get(event.getClass()) != null) {
            registeredEvents.get(event.getClass()).forEach((e, m) -> m.call(CoerceJavaToLua.coerce(event)));
        }
    }

}

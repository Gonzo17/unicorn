package gecko.listeners;

import gecko.events.Event;
import gecko.events.Events;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    Events events;
    private final String weJamminChannelId = "718185588385120296";

    public MessageListener(Events events) {
        this.events = events;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (messageSentFromBot(event)) return;
        reply(event);
    }

    private void reply(@NotNull GuildMessageReceivedEvent messageReceivedEvent) {
        if (!events.isRegistered(messageReceivedEvent.getMessage())) return;

        Event event = events.get(messageReceivedEvent);
        // TODO: Get voice channel Id of user, who requested a bot response
        VoiceChannel voiceChannel = messageReceivedEvent.getJDA().getVoiceChannelById(weJamminChannelId);
        TextChannel eventChannel = messageReceivedEvent.getChannel();

        event.executeReply(eventChannel, voiceChannel,messageReceivedEvent.getMessage());
    }

    private boolean messageSentFromBot(@NotNull GuildMessageReceivedEvent event) {
        return event.getJDA().getSelfUser().getIdLong() == event.getMember().getIdLong();
    }
}
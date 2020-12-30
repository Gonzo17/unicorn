package gecko.listeners;

import gecko.events.Event;
import gecko.events.Events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener extends ListenerAdapter {

    private final String voiceOutChannelId = "718185588385120296";

    @Autowired
    Events events;

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (messageSentFromBot(event)) return;
        reply(event);
    }

    private void reply(@NotNull GuildMessageReceivedEvent messageReceivedEvent) {
        if (!events.isRegistered(messageReceivedEvent.getMessage())) return;

        Event event = events.get(messageReceivedEvent);

        String channelId = findVoiceChannelOfUser(messageReceivedEvent.getGuild().getJDA(), messageReceivedEvent.getMember());
        VoiceChannel voiceChannel = messageReceivedEvent.getJDA().getVoiceChannelById(channelId);

        TextChannel eventChannel = messageReceivedEvent.getChannel();

        event.executeReply(eventChannel, voiceChannel, messageReceivedEvent.getMessage());
    }

    private String findVoiceChannelOfUser(JDA jda, Member member) {
        GuildVoiceState voiceState = member.getVoiceState();
        VoiceChannel voiceChannel = voiceState.getChannel();

        if (voiceChannel != null) {
            return String.valueOf(voiceChannel.getIdLong());
        }

        return voiceOutChannelId;
    }

    private boolean messageSentFromBot(@NotNull GuildMessageReceivedEvent event) {
        return event.getJDA().getSelfUser().getIdLong() == event.getMember().getIdLong();
    }
}
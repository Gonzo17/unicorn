package gecko.events;

import gecko.VoiceChannelPlayer;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class AudioEvent extends Event {
    private final String trigger;
    private final String uri;
    private final VoiceChannelPlayer voiceChannelPlayer;

    public AudioEvent(String trigger, String uri, VoiceChannelPlayer voiceChannelPlayer) {
        this.trigger = trigger;
        this.uri = uri;
        this.voiceChannelPlayer = voiceChannelPlayer;
    }

    @Override
    public String getTrigger() {
        return trigger;
    }

    @Override
    public void executeReply(GuildChannel eventChannel, GuildChannel outputVoiceChannel, Message message) {
        voiceChannelPlayer.playAudioIn((VoiceChannel) outputVoiceChannel, uri);
    }
}

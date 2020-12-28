package gecko.events;

import gecko.VoiceChannelPlayer;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class AudioEvent extends Event {
    private final String trigger;
    private final String url;
    private final VoiceChannelPlayer voiceChannelPlayer;

    public AudioEvent(String trigger, String url, VoiceChannelPlayer voiceChannelPlayer) {
        this.trigger = trigger;
        this.url = url;
        this.voiceChannelPlayer = voiceChannelPlayer;
    }

    @Override
    public String getTrigger() {
        return trigger;
    }

    @Override
    public void executeReply(GuildChannel eventChannel, GuildChannel outputVoiceChannel) {
        voiceChannelPlayer.playAudioIn((VoiceChannel) outputVoiceChannel, url);
    }
}

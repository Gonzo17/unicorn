package gecko;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoiceChannelPlayer {

    private final Settings settings;

    public VoiceChannelPlayer(@Autowired Settings settings) {
        this.settings = settings;
    }

    public void playAudioIn(VoiceChannel outputVoiceChannel, String urlOfAudio) {
        AudioManager audioManager = outputVoiceChannel.getGuild().getAudioManager();
        audioManager.openAudioConnection(outputVoiceChannel);

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioPlayer audioPlayer = playerManager.createPlayer();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
        audioManager.setSendingHandler(new AudioPlayerSendHandler(audioPlayer));
        audioPlayer.setVolume(settings.getVolume());
        playerManager.loadItem(urlOfAudio, new AudioLoadHandler(audioPlayer));
    }
}

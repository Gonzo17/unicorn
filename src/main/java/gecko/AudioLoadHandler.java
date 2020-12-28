package gecko;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.managers.AudioManager;

public class AudioLoadHandler implements AudioLoadResultHandler {
    private AudioPlayer audioPlayer;
    private AudioManager audioManager;
    private final int delay = 1000;     // Todo: Delay in Settings verfügbar machen

    public AudioLoadHandler(AudioManager audioManager, AudioPlayer audioPlayer) {
        this.audioManager = audioManager;
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void trackLoaded(AudioTrack audioTrack) {

        audioPlayer.playTrack(audioTrack);
        try {
            Thread.sleep(audioTrack.getDuration() + delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        audioManager.closeAudioConnection();
    }

    @Override
    public void playlistLoaded(AudioPlaylist audioPlaylist) {
    }

    @Override
    public void noMatches() {
    }

    @Override
    public void loadFailed(FriendlyException e) {
    }
}

package gecko;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

class ReadyListener implements EventListener {
    private int volume;

    public ReadyListener(int volume) {
       this.volume = volume;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println("API is ready!");
            for (Guild guild : event.getJDA().getGuilds()) {
                for (GuildChannel channel : guild.getChannels()) {
                    System.out.println(channel.getName() + "(" + channel.getIdLong() + ")");
                }
            }
            VoiceChannel voiceChannel = event.getJDA().getVoiceChannelById("718185588385120296");
            AudioManager audioManager = voiceChannel.getGuild().getAudioManager();
            audioManager.openAudioConnection(voiceChannel);


            AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            AudioPlayer audioPlayer = playerManager.createPlayer();
            AudioSourceManagers.registerRemoteSources(playerManager);
            AudioSourceManagers.registerLocalSource(playerManager);
            audioManager.setSendingHandler(new AudioPlayerSendHandler(audioPlayer));
            audioPlayer.setVolume(volume);
            playerManager.loadItem("https://www.youtube.com/watch?v=H6pN4tXRImA", new AudioLoadHandler(audioManager, audioPlayer));
        }
    }
}
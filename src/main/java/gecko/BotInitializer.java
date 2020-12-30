package gecko;

import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory;
import gecko.listeners.MessageListener;
import gecko.listeners.ReadyListener;
import gecko.listeners.VoiceChannelLeaveListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BotInitializer implements CommandLineRunner {

    @Autowired
    private MessageListener messageListener;

    @Override
    public void run(String... args) throws Exception {
        JDABuilder builder = JDABuilder.createDefault(args[0]);

        setUpJDABuilder(builder);
        setUpJDAEventListeners(builder);

        builder.build().awaitReady();
    }

    private void setUpJDAEventListeners(JDABuilder builder) {
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(messageListener);
        builder.addEventListeners(new VoiceChannelLeaveListener());
    }

    private void setUpJDABuilder(JDABuilder builder) {
        // Disable parts of the cache
// Wenn der Cache deaktiviert ist, dann kann der Kanal der Person, die geschrieben hat, nicht gelesen werden
//        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        builder.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("TV"));

        builder.setAudioSendFactory(new NativeAudioSendFactory());
    }
}
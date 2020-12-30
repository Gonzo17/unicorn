package gecko;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory;
import gecko.events.Events;
import gecko.listeners.MessageListener;
import gecko.listeners.ReadyListener;
import gecko.listeners.VoiceChannelLeaveListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String pathname = "src/main/resources/settings.yaml";

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        logger.info("Application is starting...");
        Settings settings = new ObjectMapper(new YAMLFactory()).readValue(new File(pathname), Settings.class);
        JDABuilder builder = JDABuilder.createDefault(args[0]);

        setUpJDABuilder(builder);
        setUpJDAEventListeners(settings, builder);

        builder.build().awaitReady();
    }

    private static void setUpJDAEventListeners(Settings settings, JDABuilder builder) {
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new MessageListener(new Events(settings)));
        builder.addEventListeners(new VoiceChannelLeaveListener());
    }

    private static void setUpJDABuilder(JDABuilder builder) {
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





package gecko;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;


public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        logger.info("Application is starting...");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Settings settings = mapper.readValue(new File("src/main/resources/settings.yaml"), Settings.class);

        JDABuilder builder = JDABuilder.createDefault(args[0]);


        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        builder.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("TV"));

        builder.setAudioSendFactory(new NativeAudioSendFactory());

        builder.addEventListeners(new ReadyListener(settings.getVolume()));
        builder.addEventListeners(new MessageListener());

        JDA jda = builder.build();
        jda.awaitReady();
    }
}





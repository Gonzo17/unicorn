package gecko.events;

import gecko.VoiceChannelPlayer;
import gecko.actions.ActionEvent;
import gecko.actions.ChangeVolumeAction;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Events {
    private static final Logger LOG
            = LoggerFactory.getLogger(Events.class);

    HashMap<String, Event> events;

    public Events(@Autowired VoiceChannelPlayer voiceChannelPlayer, @Autowired ChangeVolumeAction changeVolumeAction) {
        events = new HashMap<>();

        // TODO Events aus einer DB auslesen
        registerEvent(new ReplyEvent("unicorn", "🦄"));
        registerEvent(new ReplyEvent("ping", "pong"));
        registerEvent(new ReplyEvent("help", "Befehle:\ndrei,knaller,headshot,reden,lol,saarland,geil,stop,schnauze,toll,party,cool,kacke,gun,wer,zeit"));

        registerEvent(new AudioEvent("drei", "https://www.youtube.com/watch?v=H6pN4tXRImA", voiceChannelPlayer));
        registerEvent(new AudioEvent("knaller", "https://www.youtube.com/watch?v=RngYhEFrDtE", voiceChannelPlayer));
        registerEvent(new AudioEvent("headshot", "https://www.youtube.com/watch?v=FZU88SkLyY0", voiceChannelPlayer));
        registerEvent(new AudioEvent("reden", "https://www.youtube.com/watch?v=qzR8Jj_YFbM", voiceChannelPlayer));
        registerEvent(new AudioEvent("lol", "https://www.youtube.com/watch?v=6z2bY2qTHHo", voiceChannelPlayer));
        registerEvent(new AudioEvent("saarland", "https://www.youtube.com/watch?v=JGfd1UMib00", voiceChannelPlayer));
        registerEvent(new AudioEvent("geil", "https://www.youtube.com/watch?v=XNseG5fJQew", voiceChannelPlayer));
        registerEvent(new AudioEvent("stop", "https://www.youtube.com/watch?v=B2eFpCwIUwQ", voiceChannelPlayer));
        registerEvent(new AudioEvent("schnauze", "https://www.youtube.com/watch?v=Df62tgTdMgI", voiceChannelPlayer));
        registerEvent(new AudioEvent("toll", "https://www.youtube.com/watch?v=v9BB8JGE3Kc", voiceChannelPlayer));
        registerEvent(new AudioEvent("party", "https://www.youtube.com/watch?v=hgr4Xt0R__Y", voiceChannelPlayer));
        registerEvent(new AudioEvent("cool", "https://www.youtube.com/watch?v=QFVq9yyQIw4", voiceChannelPlayer));
        registerEvent(new AudioEvent("kacke", "https://www.youtube.com/watch?v=0UlWkGT_ANk", voiceChannelPlayer));
        registerEvent(new AudioEvent("gun", "https://www.youtube.com/watch?v=8FSjyp4fUu8", voiceChannelPlayer));
        registerEvent(new AudioEvent("wer", "https://www.youtube.com/watch?v=D8BQXcoKIF0", voiceChannelPlayer));
        registerEvent(new AudioEvent("zeit", "https://www.youtube.com/watch?v=lurNxXFBIuA", voiceChannelPlayer));

        // registerEvent(new ActionEvent("setPrefix", new ChangePrefixAction()));
        registerEvent(new ActionEvent("setVolume", changeVolumeAction));
        // Todo: Factory für verschiedene Event Typen verwenden
    }

    public void registerEvent(Event event) {
        events.put(event.getTrigger(), event);
    }

    public boolean isRegistered(Message message) {
        return events.containsKey(getTriggerWord(message));
    }

    public Event get(@NotNull GuildMessageReceivedEvent messageReceivedEvent) {
        String triggerWord = getTriggerWord(messageReceivedEvent.getMessage());
        return events.get(triggerWord);
    }

    @NotNull
    private String getTriggerWord(@NotNull Message message) {
        String messageString = message.getContentRaw();
        if(messageString.contains(" ")){
            return messageString.substring(0, messageString.indexOf(" "));
        }
        return messageString;
    }
}

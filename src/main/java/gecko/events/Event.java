package gecko.events;

import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;

public abstract class Event {

    public abstract String getTrigger();

    // Todo: Bessere Aufteilung der Parameter per Event Typ?
    public abstract void executeReply(GuildChannel eventChannel, GuildChannel voiceChannel, Message message);
}

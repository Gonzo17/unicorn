package gecko.events;

import net.dv8tion.jda.api.entities.GuildChannel;

public abstract class Event {

    public abstract String getTrigger();

    public abstract void executeReply(GuildChannel eventChannel, GuildChannel voiceChannel);
}

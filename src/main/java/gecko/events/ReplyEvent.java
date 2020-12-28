package gecko.events;

import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ReplyEvent extends Event {
    private final String trigger;
    private final String response;

    public ReplyEvent(String trigger, String response) {
        this.trigger = trigger;
        this.response = response;
    }

    @Override
    public String getTrigger() {
        return trigger;
    }

    @Override
    public void executeReply(GuildChannel channel, GuildChannel voiceChannel, Message message) {
        TextChannel textChannel = (TextChannel) channel;
        textChannel.sendMessage(response).queue();
    }
}

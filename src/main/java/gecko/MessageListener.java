package gecko;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if ("test".equals(event.getChannel().getName())) {
            System.out.println("Message in test channel: " + event.getMessage());
            if (event.getJDA().getSelfUser().getIdLong() != event.getMember().getIdLong()) {
                event.getChannel().sendMessage("🦄").queue();
            }
        }
    }
}
package gecko;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

class ReadyListener implements EventListener {

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println("API is ready!");
            for (Guild guild : event.getJDA().getGuilds()) {
                for (GuildChannel channel : guild.getChannels()) {
                    System.out.printf("%s(%d)%n", channel.getName(), channel.getIdLong());
                }
            }
        }
    }
}
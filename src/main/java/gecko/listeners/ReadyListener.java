package gecko.listeners;

import gecko.actions.ChangeVolumeAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyListener implements EventListener {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeVolumeAction.class);

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            LOG.info("API is ready!");
            for (Guild guild : event.getJDA().getGuilds()) {
                for (GuildChannel channel : guild.getChannels()) {
                    LOG.info("%s(%d)".formatted(channel.getName(), channel.getIdLong()));
                }
            }
        }
    }
}
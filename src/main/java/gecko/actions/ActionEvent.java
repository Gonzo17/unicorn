package gecko.actions;

import gecko.events.Event;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;

public class ActionEvent extends Event {
    private final String trigger;
    private final IAction action;

    public ActionEvent(String trigger, IAction action) {
        this.trigger = trigger;
        this.action = action;
    }

    @Override
    public String getTrigger() {
        return trigger;
    }

    @Override
    public void executeReply(GuildChannel eventChannel, GuildChannel voiceChannel, Message message) {
        action.execute(message);
    }
}

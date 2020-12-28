package gecko.actions;

import net.dv8tion.jda.api.entities.Message;

public interface IAction {
    void execute(Message message);
}

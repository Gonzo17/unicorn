package gecko.actions;

import gecko.Settings;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangePrefixAction extends SettingAction {

    public ChangePrefixAction(@Autowired Settings settings) {
        super(settings);
    }

    @Override
    public void execute(Message message) {

    }
}

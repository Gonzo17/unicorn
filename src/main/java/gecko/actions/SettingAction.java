package gecko.actions;

import gecko.Settings;
import net.dv8tion.jda.api.entities.Message;

public abstract class SettingAction implements IAction {

    private final Settings settings;

    public SettingAction(Settings settings) {
        this.settings = settings;
    }

    public abstract void execute(Message message);

    public void setVolume(int volume) {
        settings.setVolume(volume);
    }
}

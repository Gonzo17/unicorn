package gecko.actions;

import gecko.Settings;
import net.dv8tion.jda.api.entities.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeVolumeAction implements IAction {

    private static final Logger LOG
            = LoggerFactory.getLogger(ChangeVolumeAction.class);

    private Settings settings;

    public ChangeVolumeAction(@Autowired Settings settings) {
        this.settings = settings;
    }

    @Override
    public void execute(Message message) {
        LOG.info("Execute ChangeVolumeAction");
        try {
            int volume = extractVolumeFromMessage(message.getContentRaw());

            settings.setVolume(volume);
        } catch (NumberFormatException e) {
            System.out.printf("Can't set volume with message: %s%n", message.getContentRaw());
        }
//        int volume = message.getContentStripped();
//        int volume = message.getContentDisplay();
        // Todo: Welchen Unterschied gibt es hier?

        // Todo: Trigger SettingsChanged Event oder so?
    }

    private int extractVolumeFromMessage(String message) throws NumberFormatException {
        String trimmedMessage = message.replaceFirst("setVolume ", "");
        return Integer.parseInt(trimmedMessage);
    }
}

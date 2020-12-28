package gecko.actions;

import gecko.Settings;
import net.dv8tion.jda.api.entities.Message;

public class ChangeVolumeAction extends SettingAction {
    public ChangeVolumeAction(Settings settings) {
        super(settings);
    }

    @Override
    public void execute(Message message) {
        try {
            int volume = extractVolumeFromMessage(message.getContentRaw());

            super.setVolume(volume);    // Todo: Geht das in Java auch anders?
        } catch (NumberFormatException e) {
            System.out.printf("Can't set volume with message: %s%n", message.getContentRaw());
        }
//        int volume = message.getContentStripped();
//        int volume = message.getContentDisplay();
        // Todo: Welchen Unterschied gibt es hier?

        // Todo: Trigger SettingsChanged Event oder so?
    }

    private int extractVolumeFromMessage(String message)throws NumberFormatException {
        String trimmedMessage = message.replaceFirst("setVolume ", "");
        return Integer.parseInt(trimmedMessage);
    }
}

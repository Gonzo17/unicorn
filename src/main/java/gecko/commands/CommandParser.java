package gecko.commands;
import org.apache.logging.log4j.util.Strings;

public class CommandParser {

    public static String extractArgumentOfCommand(String command, String triggerWord) {
        String commandWithoutTriggerWord = command.replaceFirst(triggerWord, Strings.EMPTY);
        return commandWithoutTriggerWord.trim();
    }
}

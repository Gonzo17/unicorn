package gecko.commands;


import gecko.actions.ChangeVolumeAction;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class CommandParserTest {
    private String volumeTriggerWord;

    @Before
    public void setUp () {
        volumeTriggerWord = ChangeVolumeAction.getTriggerWord();
    }

    @Test
    public void extractArgumentOfCommand_ValueIs_1 () {
        var volume = extractArgumentFromInput("setVolume 1");
        assertThat(volume, is("1"));
    }

    @Test
    public void extractArgumentOfCommand_ValueIs_50 () {
        var volume = extractArgumentFromInput("setVolume 50");
        assertThat(volume, is("50"));
    }

    @Test
    public void extractArgumentOfCommand_ValueIs_50_DoubleSpacesInBetween () {
        var volume = extractArgumentFromInput("setVolume  50");
        assertThat(volume, is("50"));
    }

    @Test
    public void extractArgumentOfCommand_ValueIs_50_MultipleSpacesBetweenAndAfter () {
        var volume = extractArgumentFromInput("setVolume      50     ");
        assertThat(volume, is("50"));
    }

    @Test
    public void extractArgumentOfCommand_ValueIs_ABC () {
        var volume = extractArgumentFromInput("setVolume ABC");
        assertThat(volume, is("ABC"));
    }

    private String extractArgumentFromInput(String input) {
        return CommandParser.extractArgumentOfCommand(input, volumeTriggerWord);
    }

}
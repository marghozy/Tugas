import java.util.Arrays;

public final class CommandWords {
    private static final String[] validCommands = {
        "go", "quit", "help"
    };

    public CommandWords() {
        // nothing to do at the moment...
    }

    public boolean isCommand(String aString) {
        return Arrays.asList(validCommands).contains(aString);
    }
}

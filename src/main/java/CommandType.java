import java.util.Arrays;

public enum CommandType {
    LS("ls"),
    MKDIR("mkdir"),
    CD("cd"),
    CAT("cat"),
    TOUCH("touch");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandType byCommand(String command) {
        return Arrays.stream(values())
                .filter(cmd -> cmd.getCommand().equals(command))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Command not found"));
    }
}

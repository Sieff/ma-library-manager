package LibraryManager.ui;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 * A class to create a user interface.
 * The start method is the entry point.
 * A tool can have some or all of the following responsibilities:
 *  - Display information to the user via the print methods.
 *  - Query the user for inputs via the input methods and handle them accordingly.
 * Tools can call other tools to create nested user interfaces.
 * To exit a tool, return in the start method.
 * To restart a tool, call the start method again.
 */
public abstract class AbstractUi {
    private final TextIO textIO = TextIoFactory.getTextIO();

    /**
     * Entry point to start the tool.
     * Implements the behaviour of the user interface.
     */
    abstract public void start();

    /**
     * Print a line of text to the terminal
     * @param text The string to be printed
     */
    protected void println(String text) {
        TextTerminal<?> terminal = textIO.getTextTerminal();
        terminal.println(text);
    }

    /**
     * Print a text to the terminal
     * @param text The string to be printed
     */
    protected void print(String text) {
        TextTerminal<?> terminal = textIO.getTextTerminal();
        terminal.print(text);
    }

    /**
     * Print a formatted text to the terminal
     * @param text The string to be printed
     */
    protected void printf(String text, Object... args) {
        TextTerminal<?> terminal = textIO.getTextTerminal();
        terminal.printf(text, args);
    }

    /**
     * Read an enum input from the user
     * @param enumClass The class of the enum
     * @param prompt The prompt that is given to the user
     * @return The resulting enum value of the user input
     * @param <T> The type parameter of the enum
     */
    protected <T extends Enum<T>> T enumInput(Class<T> enumClass, String prompt) {
        return textIO.newEnumInputReader(enumClass).read(prompt);
    }

    /**
     * Read a boolean input from the user
     * @param prompt The prompt that is given to the user
     * @return The resulting boolean value of the user input
     */
    protected boolean booleanInput(String prompt) {
        return textIO.newBooleanInputReader().read(prompt);
    }

    /**
     * Read a string input from the user
     * @param prompt The prompt that is given to the user
     * @param allowEmpty Determine whether an empty string is allowed as an input
     * @return The resulting string value of the user input
     */
    protected String stringInput(String prompt, boolean allowEmpty) {
        if (allowEmpty) {
            return textIO.newStringInputReader().withMinLength(0).read(prompt);
        } else {
            return textIO.newStringInputReader().read(prompt);
        }
    }

    /**
     * Read an integer input from the user
     * @param prompt The prompt that is given to the user
     * @return The resulting integer value of the user input
     */
    protected Integer integerInput(String prompt) {
        return textIO.newIntInputReader().read(prompt);
    }

    /**
     * Read an integer input from the user
     * @param prompt The prompt that is given to the user
     * @return The resulting integer value of the user input
     */
    protected Integer integerInput(String prompt, Integer defaultValue) {
        return textIO.newIntInputReader().withDefaultValue(defaultValue).read(prompt);
    }

    // TODO: Implement more textIO inputs if needed
}

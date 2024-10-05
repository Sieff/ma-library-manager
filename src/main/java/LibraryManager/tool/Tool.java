package LibraryManager.tool;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public abstract class Tool {
    protected TextIO textIO = TextIoFactory.getTextIO();

    abstract public void start();

    protected void println(String text) {
        TextTerminal<?> terminal = textIO.getTextTerminal();
        terminal.println(text);
    }

    protected void print(String text) {
        TextTerminal<?> terminal = textIO.getTextTerminal();
        terminal.print(text);
    }

    protected void printf(String text, Object... args) {
        TextTerminal<?> terminal = textIO.getTextTerminal();
        terminal.printf(text, args);
    }

    protected <T extends Enum<T>> T enumInput(Class<T> enumClass, String prompt) {
        return textIO.newEnumInputReader(enumClass).read(prompt);
    }

    protected boolean booleanInput(String prompt) {
        return textIO.newBooleanInputReader().read(prompt);
    }

    protected String stringInput(String prompt, boolean allowEmpty) {
        if (allowEmpty) {
            return textIO.newStringInputReader().withMinLength(0).read(prompt);
        } else {
            return textIO.newStringInputReader().read(prompt);
        }
    }

    protected Integer integerInput(String prompt) {
        return textIO.newIntInputReader().read(prompt);
    }
}

package LibraryManager.tool;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public abstract class Tool {
    protected TextIO textIO = TextIoFactory.getTextIO();

    abstract public void start();

    public Tool withParameter(Object param) {
        return this;
    }

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
}

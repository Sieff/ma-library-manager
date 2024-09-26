package LibraryManager.tool;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public abstract class Tool<T> {
    protected TextIO textIO = TextIoFactory.getTextIO();

    abstract protected T showForm();
    abstract protected void handleResult(T result);

    public void start() {
        T result = showForm();
        handleResult(result);
    }

    public Tool<T> withParameter(Object param) {
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

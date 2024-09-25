package LibraryManager.menu;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

abstract class Menu<T extends MainMenuOption> {
    protected TextIO textIO = TextIoFactory.getTextIO();

    abstract void start();
    abstract void choose(T option);

    void printLine(String text) {
        TextTerminal terminal = textIO.getTextTerminal();
        terminal.printf(text + "\n");
    }
}

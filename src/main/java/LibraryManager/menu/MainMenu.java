package LibraryManager.menu;

public class MainMenu extends Menu<MainMenuOption> {
    @Override
    public void start() {
        MainMenuOption option = textIO.newEnumInputReader(MainMenuOption.class)
                .read("What do you want to do?");
        choose(option);
    }

    @Override
    public void choose(MainMenuOption option) {
        switch (option) {
            case ADD -> {
                printLine("Ja moin");
            }
            case REMOVE -> {
                printLine("Ja moin 2");
            }
            case LEIH -> {
                printLine("Ja moin 3");
            }
        }
    }
}

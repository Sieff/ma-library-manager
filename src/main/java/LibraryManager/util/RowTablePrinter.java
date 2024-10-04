package LibraryManager.util;

import java.util.ArrayList;
import java.util.List;

public class RowTablePrinter extends TablePrinter {
    @Override
    protected List<String> getDataLines(List<Integer> columnWidths)  {
        List<StringBuilder> lines = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            lines.add(new StringBuilder());
        }
        for (int line = 0; line < table.size(); line++) {
            for (int column = 0; column < table.get(line).size(); column++) {
                renderEntry(lines.get(line), table.get(line).get(column), columnWidths.get(column), column == 0, column == table.get(line).size() - 1);
            }
        }

        for (StringBuilder line : lines) {
            line.append("\n");
        }

        return lines.stream().map(StringBuilder::toString).toList();
    }

    @Override
    protected List<Integer> getColumnWidths() {
        List<Integer> headerWidths = headers.stream().map(String::length).toList();

        List<Integer> columnWidths = new ArrayList<>(table.getFirst().size());
        for (int i = 0; i < table.getFirst().size(); i++) {
            columnWidths.add(0);
        }
        for (int line = 0; line < table.size(); line++) {
            for (int column = 0; column < table.get(line).size(); column++) {
                Integer entryLength = table.get(line).get(column).length();

                if (entryLength > columnWidths.get(column)) {
                    columnWidths.set(line, entryLength);
                }
            }
        }

        return getRenderedColumnWidths(headerWidths, columnWidths);
    }
}

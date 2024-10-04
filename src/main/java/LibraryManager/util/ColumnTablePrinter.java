package LibraryManager.util;

import java.util.ArrayList;
import java.util.List;

public class ColumnTablePrinter extends TablePrinter {
    @Override
    protected List<String> getDataLines(List<Integer> columnWidths)  {
        List<StringBuilder> lines = new ArrayList<>();
        for (int i = 0; i < table.getFirst().size(); i++) {
            lines.add(new StringBuilder());
        }
        for (int column = 0; column < table.size(); column++) {
            for (int line = 0; line < table.get(column).size(); line++) {
                renderEntry(lines.get(line), table.get(column).get(line), columnWidths.get(column), column == 0, column == table.size() - 1);
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
        List<Integer> columnWidths = table.stream().map(
                column -> column.stream().map(String::length).max(Integer::compareTo).orElse(0)
        ).toList();

        return getRenderedColumnWidths(headerWidths, columnWidths);
    }
}

package LibraryManager.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A TableStringBuilder to create tables that are represented as a list of columns.
 * Adding a data point here means adding a column in the rendered table.
 * Each column should have the same amount of entries, which represent the rows.
 */
public class ColumnTableStringBuilder extends TableStringBuilder {
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
    protected void validateTable() {
        if (table.isEmpty()) {
            throw new RuntimeException("No columns provided");
        }
        if (headers.isEmpty() || headers.size() != table.size()) {
            throw new RuntimeException("Number of headers does not match number of columns");
        }
        int rows = table.getFirst().size();
        for (List<String> column : table) {
            if (column.size() != rows) {
                throw new RuntimeException("Number of rows does not match between columns");
            }
        }
    }

    @Override
    protected List<Integer> getColumnWidths() {
        List<Integer> columnWidths = table.stream().map(
                column -> column.stream().map(String::length).max(Integer::compareTo).orElse(0)
        ).toList();

        List<Integer> headerWidths = headers.stream().map(String::length).toList();
        return getRenderedColumnWidths(headerWidths, columnWidths);
    }
}

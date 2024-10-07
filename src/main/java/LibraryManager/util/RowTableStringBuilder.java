package LibraryManager.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A TableStringBuilder to create tables that are represented as a list of rows.
 * Adding a data point here means adding a row in the rendered table.
 * Each row should have the same amount of entries, which represent the columns.
 */
public class RowTableStringBuilder extends TableStringBuilder {
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
    protected void validateTable() {
        if (table.isEmpty()) {
            throw new RuntimeException("No rows provided");
        }
        if (headers.isEmpty() || headers.size() != table.getFirst().size()) {
            throw new RuntimeException("Number of headers does not match number of columns");
        }
        int columns = table.getFirst().size();
        for (List<String> row : table) {
            if (row.size() != columns) {
                throw new RuntimeException("Number of columns does not match between rows");
            }
        }
    }

    @Override
    protected List<Integer> getColumnWidths() {
        List<Integer> columnWidths = new ArrayList<>(table.getFirst().size());
        for (int i = 0; i < table.getFirst().size(); i++) {
            columnWidths.add(0);
        }
        for (int line = 0; line < table.size(); line++) {
            for (int column = 0; column < table.get(line).size(); column++) {
                Integer entryLength = table.get(line).get(column).length();

                if (entryLength > columnWidths.get(column)) {
                    columnWidths.set(column, entryLength);
                }
            }
        }

        List<Integer> headerWidths = headers.stream().map(String::length).toList();
        return getRenderedColumnWidths(headerWidths, columnWidths);
    }
}

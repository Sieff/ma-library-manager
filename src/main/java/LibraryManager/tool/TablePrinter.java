package LibraryManager.tool;

import org.beryx.textio.TextTerminal;

import java.util.ArrayList;
import java.util.List;

public class TablePrinter {
    private final List<List<String>> table = new ArrayList<>();
    private List<String> headers = new ArrayList<>();
    private int maxColumnWidth = 40;

    public void addColumn(List<String> values) {
        table.add(values);
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void setMaxColumnWidth(int maxColumnWidth) {
        this.maxColumnWidth = maxColumnWidth;
    }

    public void print(TextTerminal<?> terminal) {
        if (table.isEmpty()) {
            throw new RuntimeException("No columns provided");
        }
        if (!headers.isEmpty() && headers.size() != table.size()) {
            throw new RuntimeException("Number of headers does not match number of columns");
        }
        int rows = table.getFirst().size();
        for (List<String> column : table) {
            if (column.size() != rows) {
                throw new RuntimeException("Number of rows does not match between columns");
            }
        }

        List<Integer> columnWidths = getColumnWidths();

        String dividerLine = getDividerLine(columnWidths);

        StringBuilder result = new StringBuilder();

        result.append(dividerLine);

        result.append(getHeaderLine(columnWidths));

        result.append(dividerLine);

        List<String> dataLines = getDataLines(columnWidths);
        for (String line : dataLines) {
            result.append(line);
        }

        result.append(dividerLine);

        terminal.println(result.toString());
    }

    private String getHeaderLine(List<Integer> columnWidths) {
        StringBuilder headerLine = new StringBuilder();
        for (int column = 0; column < headers.size(); column++) {
            renderEntry(headerLine, headers.get(column), columnWidths.get(column), column == 0, column == headers.size() - 1);
        }
        headerLine.append("\n");
        return headerLine.toString();
    }

    private List<String> getDataLines(List<Integer> columnWidths) {
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

    private String getDividerLine(List<Integer> columnWidths) {
        StringBuilder dividerLine = new StringBuilder();
        for (Integer columnWidth : columnWidths) {
            dividerLine.append("+");
            dividerLine.append("=".repeat(2 + columnWidth));
        }
        dividerLine.append("+\n");
        return dividerLine.toString();
    }

    private void renderEntry(StringBuilder target, String entry, int columnWidth, boolean isFirst, boolean isLast) {
        if (isFirst) {
            target.append("| ");
        } else {
            target.append(" | ");
        }

        if (entry.length() > columnWidth) {
            target.append(entry, 0, columnWidth - 3).append("...");
        } else if (entry.length() < columnWidth) {
            target.append(entry);
            target.append(" ".repeat(columnWidth - entry.length()));
        } else {
            target.append(entry);
        }

        if (isLast) {
            target.append(" |");
        }
    }

    private List<Integer> getColumnWidths() {
        List<Integer> headerWidths = headers.stream().map(String::length).toList();
        List<Integer> columnWidths = table.stream().map(
                column -> column.stream().map(String::length).max(Integer::compareTo).orElse(0)
        ).toList();

        List<Integer> widths = new ArrayList<>();
        for (int i = 0; i < headerWidths.size(); i++) {
            if (headerWidths.get(i) < maxColumnWidth && headerWidths.get(i) > columnWidths.get(i)) {
                widths.add(headerWidths.get(i));
                continue;
            }
            if (columnWidths.get(i) < maxColumnWidth && columnWidths.get(i) > headerWidths.get(i)) {
                widths.add(columnWidths.get(i));
                continue;
            }
            widths.add(maxColumnWidth);
        }

        return widths;
    }
}

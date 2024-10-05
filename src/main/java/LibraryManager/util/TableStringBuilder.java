package LibraryManager.util;

import java.util.ArrayList;
import java.util.List;

public abstract class TableStringBuilder {
    protected final List<List<String>> table = new ArrayList<>();
    protected List<String> headers = new ArrayList<>();
    protected int maxColumnWidth = 40;

    protected abstract List<Integer> getColumnWidths();
    protected abstract List<String> getDataLines(List<Integer> columnWidths);
    protected abstract void validateTable();

    public void addDataPoint(List<String> values) {
        table.add(values);
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void setMaxColumnWidth(int maxColumnWidth) {
        this.maxColumnWidth = maxColumnWidth;
    }

    @Override
    public String toString() {
        validateTable();

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

        return result.toString();
    }

    private String getHeaderLine(List<Integer> columnWidths) {
        StringBuilder headerLine = new StringBuilder();
        for (int column = 0; column < headers.size(); column++) {
            renderEntry(headerLine, headers.get(column), columnWidths.get(column), column == 0, column == headers.size() - 1);
        }
        headerLine.append("\n");
        return headerLine.toString();
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

    protected void renderEntry(StringBuilder target, String entry, int columnWidth, boolean isFirst, boolean isLast) {
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

    protected List<Integer> getRenderedColumnWidths(List<Integer> headerWidths, List<Integer> columnWidths) {
        List<Integer> widths = new ArrayList<>();
        for (int i = 0; i < columnWidths.size(); i++) {
            if (headerWidths.get(i) < maxColumnWidth && headerWidths.get(i) >= columnWidths.get(i)) {
                widths.add(headerWidths.get(i));
                continue;
            }
            if (columnWidths.get(i) < maxColumnWidth && columnWidths.get(i) >= headerWidths.get(i)) {
                widths.add(columnWidths.get(i));
                continue;
            }
            widths.add(maxColumnWidth);
        }
        return widths;
    }

    protected List<Integer> getRenderedColumnWidths(List<Integer> columnWidths) {
        List<Integer> widths = new ArrayList<>();
        for (Integer columnWidth : columnWidths) {
            if (columnWidth < maxColumnWidth) {
                widths.add(columnWidth);
            } else {
                widths.add(maxColumnWidth);
            }
        }
        return widths;
    }
}

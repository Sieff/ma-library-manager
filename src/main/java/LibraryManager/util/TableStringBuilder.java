package LibraryManager.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Strings of Table data.
 * Data is represented as a list fo lists of strings.
 * Table headers are a list of strings.
 */
public abstract class TableStringBuilder {
    protected final List<List<String>> table = new ArrayList<>();
    protected List<String> headers = new ArrayList<>();
    protected int maxColumnWidth = 40;

    /**
     * Get the rendered widths of each column to align each entry.
     * @return A list of widths for each column of the table
     */
    protected abstract List<Integer> getColumnWidths();

    /**
     * Create a string for each row of data in the table
     * @param columnWidths The widths for each column of the table
     * @return A list of strings, which are rendered data rows
     */
    protected abstract List<String> getDataLines(List<Integer> columnWidths);

    /**
     * Check if the table can be rendered
     */
    protected abstract void validateTable();

    /**
     * Add a data point to the table
     * @param values The values to be appended to the table data
     */
    public void addDataPoint(List<String> values) {
        table.add(values);
    }

    /**
     * Set the headers of the table.
     * Should match the number of columns in the table.
     * @param headers A list of strings for the headers of the table.
     */
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    /**
     * Set the maximum rendered width for each column in the table.
     * When an entry exceeds that width, it should get cut off.
     * @param maxColumnWidth The maximum column width
     */
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

    /**
     * Get the string for the rendered headers of the table
     * @param columnWidths The widths for each column of the table
     * @return A string of the headers rendered for the table
     */
    private String getHeaderLine(List<Integer> columnWidths) {
        StringBuilder headerLine = new StringBuilder();
        for (int column = 0; column < headers.size(); column++) {
            renderEntry(headerLine, headers.get(column), columnWidths.get(column), column == 0, column == headers.size() - 1);
        }
        headerLine.append("\n");
        return headerLine.toString();
    }

    /**
     * Get the string for the rendered divider line of the table
     * @param columnWidths The widths for each column of the table
     * @return A string of the rendered divider line for the table
     */
    private String getDividerLine(List<Integer> columnWidths) {
        StringBuilder dividerLine = new StringBuilder();
        for (Integer columnWidth : columnWidths) {
            dividerLine.append("+");
            dividerLine.append("=".repeat(2 + columnWidth));
        }
        dividerLine.append("+\n");
        return dividerLine.toString();
    }

    /**
     * Renders one entry for within a line of the table
     * @param out The StringBuilder of the line of the entry
     * @param entry The entry to be rendered
     * @param columnWidth Maximum width of the column for the entry
     * @param isFirst Whether the entry is the first in the line
     * @param isLast Whether the entry is the last in the line
     */
    protected void renderEntry(StringBuilder out, String entry, int columnWidth, boolean isFirst, boolean isLast) {
        if (isFirst) {
            out.append("| ");
        } else {
            out.append(" | ");
        }

        if (entry.length() > columnWidth) {
            out.append(entry, 0, columnWidth - 3).append("...");
        } else if (entry.length() < columnWidth) {
            out.append(entry);
            out.append(" ".repeat(columnWidth - entry.length()));
        } else {
            out.append(entry);
        }

        if (isLast) {
            out.append(" |");
        }
    }

    /**
     * Get the rendered column widths depending on the header widths, column widths and maximum column width
     * @param headerWidths A list of integers for the string lengths of the headers
     * @param columnWidths A list of integers for the maximum string lengths for each column
     * @return A list of integers that represents the widths of columns in the rendered table
     */
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
}

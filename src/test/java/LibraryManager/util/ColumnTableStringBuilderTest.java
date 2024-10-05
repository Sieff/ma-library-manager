package LibraryManager.util;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ColumnTableStringBuilderTest {
    @Test
    void testEmpty() {
        ColumnTableStringBuilder columnTableStringBuilder = new ColumnTableStringBuilder();

        try {
            columnTableStringBuilder.toString();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }

        columnTableStringBuilder.setHeaders(List.of(""));

        try {
            columnTableStringBuilder.toString();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }
    }

    @Test
    void testNormal() {
        ColumnTableStringBuilder columnTableStringBuilder = new ColumnTableStringBuilder();
        columnTableStringBuilder.setHeaders(List.of("", ""));
        columnTableStringBuilder.addDataPoint(List.of(""));
        columnTableStringBuilder.addDataPoint(List.of(""));

        try {
            columnTableStringBuilder.toString();
            assert true;
        } catch (Exception ignored) {
            assert false;
        }
    }

    @Test
    void testUnequalHeadersAndColumns() {
        ColumnTableStringBuilder columnTableStringBuilder = new ColumnTableStringBuilder();
        columnTableStringBuilder.setHeaders(List.of("", ""));
        columnTableStringBuilder.addDataPoint(List.of(""));

        try {
            columnTableStringBuilder.toString();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }
    }

    @Test
    void testInconsistentRows() {
        ColumnTableStringBuilder columnTableStringBuilder = new ColumnTableStringBuilder();
        columnTableStringBuilder.setHeaders(List.of("", ""));
        columnTableStringBuilder.addDataPoint(List.of(""));
        columnTableStringBuilder.addDataPoint(List.of("", ""));

        try {
            columnTableStringBuilder.toString();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }
    }
}

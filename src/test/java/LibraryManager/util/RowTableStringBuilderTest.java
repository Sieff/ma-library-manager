package LibraryManager.util;

import org.junit.jupiter.api.Test;

import java.util.List;

public class RowTableStringBuilderTest {
    @Test
    void testEmpty() {
        RowTableStringBuilder rowTableStringBuilder = new RowTableStringBuilder();

        try {
            rowTableStringBuilder.toString();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }

        rowTableStringBuilder.setHeaders(List.of(""));

        try {
            rowTableStringBuilder.toString();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }
    }

    @Test
    void testNormal() {
        RowTableStringBuilder rowTableStringBuilder = new RowTableStringBuilder();
        rowTableStringBuilder.setHeaders(List.of("", ""));
        rowTableStringBuilder.addDataPoint(List.of("", ""));

        try {
            rowTableStringBuilder.toString();
            assert true;
        } catch (Exception ignored) {
            assert false;
        }
    }

    @Test
    void testUnequalHeadersAndColumns() {
        RowTableStringBuilder rowTableStringBuilder = new RowTableStringBuilder();
        rowTableStringBuilder.setHeaders(List.of("", ""));
        rowTableStringBuilder.addDataPoint(List.of(""));
        rowTableStringBuilder.addDataPoint(List.of(""));

        try {
            rowTableStringBuilder.toString();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }
    }

    @Test
    void testInconsistentColumns() {
        RowTableStringBuilder rowTableStringBuilder = new RowTableStringBuilder();
        rowTableStringBuilder.setHeaders(List.of("", ""));
        rowTableStringBuilder.addDataPoint(List.of(""));
        rowTableStringBuilder.addDataPoint(List.of("", ""));

        try {
            rowTableStringBuilder.toString();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }
    }
}

package LibraryManager.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ColumnTableStringBuilderTest {
    @Test
    void testEmpty() {
        ColumnTableStringBuilder columnTableStringBuilder = new ColumnTableStringBuilder();

        try {
            columnTableStringBuilder.render();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }

        columnTableStringBuilder.setHeaders(List.of(""));

        try {
            columnTableStringBuilder.render();
            assert false;
        } catch (Exception ignored) {
            assert true;
        }

        ColumnTableStringBuilder columnTableStringBuilder2 = new ColumnTableStringBuilder();
        columnTableStringBuilder2.setHeaders(List.of());
        columnTableStringBuilder2.addDataPoint(List.of(""));

        try {
            columnTableStringBuilder2.render();
            fail("Expected RuntimeException to be thrown");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void testNormal() {
        ColumnTableStringBuilder columnTableStringBuilder = new ColumnTableStringBuilder();
        columnTableStringBuilder.setHeaders(List.of("", ""));
        columnTableStringBuilder.addDataPoint(List.of(""));
        columnTableStringBuilder.addDataPoint(List.of(""));

        try {
            columnTableStringBuilder.render();
        } catch (Exception e) {
            fail("Expected no exception to be thrown");
        }
    }

    @Test
    void testUnequalHeadersAndColumns() {
        ColumnTableStringBuilder columnTableStringBuilder = new ColumnTableStringBuilder();
        columnTableStringBuilder.setHeaders(List.of("", ""));
        columnTableStringBuilder.addDataPoint(List.of(""));

        try {
            columnTableStringBuilder.render();
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }

    @Test
    void testInconsistentRows() {
        ColumnTableStringBuilder columnTableStringBuilder = new ColumnTableStringBuilder();
        columnTableStringBuilder.setHeaders(List.of("", ""));
        columnTableStringBuilder.addDataPoint(List.of(""));
        columnTableStringBuilder.addDataPoint(List.of("", ""));

        try {
            columnTableStringBuilder.render();
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }
}

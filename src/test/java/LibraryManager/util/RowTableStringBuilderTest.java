package LibraryManager.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class RowTableStringBuilderTest {
    @Test
    void testEmpty() {
        RowTableStringBuilder rowTableStringBuilder = new RowTableStringBuilder();

        try {
            rowTableStringBuilder.render();
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }

        rowTableStringBuilder.setHeaders(List.of(""));

        try {
            rowTableStringBuilder.render();
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }

        RowTableStringBuilder rowTableStringBuilder2 = new RowTableStringBuilder();
        rowTableStringBuilder2.setHeaders(List.of());
        rowTableStringBuilder2.addDataPoint(List.of(""));

        try {
            rowTableStringBuilder2.render();
            fail("Expected RuntimeException to be thrown");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    void testNormal() {
        RowTableStringBuilder rowTableStringBuilder = new RowTableStringBuilder();
        rowTableStringBuilder.setHeaders(List.of("", ""));
        rowTableStringBuilder.addDataPoint(List.of("", ""));

        try {
            rowTableStringBuilder.render();
        } catch (Exception e) {
            fail("Expected no exception to be thrown");
        }
    }

    @Test
    void testUnequalHeadersAndColumns() {
        RowTableStringBuilder rowTableStringBuilder = new RowTableStringBuilder();
        rowTableStringBuilder.setHeaders(List.of("", ""));
        rowTableStringBuilder.addDataPoint(List.of(""));
        rowTableStringBuilder.addDataPoint(List.of(""));

        try {
            rowTableStringBuilder.render();
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }

    @Test
    void testInconsistentColumns() {
        RowTableStringBuilder rowTableStringBuilder = new RowTableStringBuilder();
        rowTableStringBuilder.setHeaders(List.of("", ""));
        rowTableStringBuilder.addDataPoint(List.of(""));
        rowTableStringBuilder.addDataPoint(List.of("", ""));

        try {
            rowTableStringBuilder.render();
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }
}

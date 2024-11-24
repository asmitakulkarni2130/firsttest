import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class GoogleSearchTest {

    @Test
    @Test
    void getFirstResultTitleShouldReturnValidTitle() throws IOException {
        // Mock valid JSON response
        String mockResponse = """
    {
        "items": [
            {
                "title": "JUnit 5 User Guide"
            }
        ]
    }
    """;

        // Call getFirstResultTitle
        String title = GoogleSearch.getFirstResultTitle(mockResponse);

        // Validate title
        assertEquals("JUnit 5 User Guide", title, "Title should match the mock response");
    }

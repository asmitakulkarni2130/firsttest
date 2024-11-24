import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class GoogleSearchTest {

    @Test
    void searchShouldReturnResponse() throws Exception {
        // Mock Query (replace with your actual API_KEY and CX to test)
        String query = "JUnit 5";

        // Call the search method
        String response = GoogleSearch.search(query);

        // Validate response
        assertNotNull(response, "Response should not be null");
        assertTrue(response.contains("items"), "Response should contain 'items'");
    }

    @Test
    void getFirstResultTitleShouldReturnValidTitle() throws IOException {
        // Mock Response JSON (partial example response from Google Custom Search API)
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

    @Test
    void getFirstResultTitleShouldHandleNoResults() throws IOException {
        // Mock Response JSON with no results
        String mockResponse = """
        {
            "items": []
        }
        """;

        // Call getFirstResultTitle
        String title = GoogleSearch.getFirstResultTitle(mockResponse);

        // Validate title
        assertEquals("No results found", title, "Should handle no results gracefully");
    }
}

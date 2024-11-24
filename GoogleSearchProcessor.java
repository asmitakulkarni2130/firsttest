import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchProcessor {

    public static List<SearchResult> processSearchResults(JsonNode jsonResponse) {
        List<SearchResult> results = new ArrayList<>();

        JsonNode items = jsonResponse.get("items");
        if (items != null) {
            for (JsonNode item : items) {
                SearchResult result = new SearchResult();
                result.setTitle(item.get("title").asText());
                result.setLink(item.get("link").asText());
                result.setSnippet(item.get("snippet").asText());
                results.add(result);
            }
        }

        return results;
    }
}
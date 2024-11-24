import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GoogleSearch {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String CX = "YOUR_CX";

    public static String search(String query) throws IOException, InterruptedException {
        String url = "https://www.googleapis.com/customsearch/v1?q=" + query +
                "&key=" + API_KEY + "&cx=" + CX;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to fetch results. HTTP Status Code: " + response.statusCode());
        }

        // Return the response body (assumes it is JSON)
        return response.body();
    }


    public static String getFirstResultTitle(String mockResponse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        rootNode = mapper.readTree(mockResponse);

        // Navigate to the first result's title
        JsonNode itemsNode = rootNode.get("items");
        if (itemsNode != null && itemsNode.isArray() && itemsNode.size() > 0) {
            return itemsNode.get(0).get("title").asText();
        }
        return "No results found";
    }


}



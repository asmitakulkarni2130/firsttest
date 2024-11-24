import java.io.IOException;

public class GoogleSearchApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a search query.");
            return;
        }

        String query = args[0];  // Search query from command line arguments

        try {
            // Perform search and get raw JSON response
            String jsonResponse = GoogleSearch.search(query);

            // Display results
            System.out.println("Search results for: " + query);
            parseAndDisplayResults(jsonResponse);

        } catch (IOException e) {
            System.out.println("An error occurred while performing the search: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseAndDisplayResults(String jsonResponse) {
        // Basic parsing logic (you can enhance this)
        String[] items = jsonResponse.split("\"items\":\\[")[1].split("],")[0].split("\\},\\{");

        for (String item : items) {
            String title = extractValue(item, "\"title\":\"");
            String link = extractValue(item, "\"link\":\"");
            String snippet = extractValue(item, "\"snippet\":\"");

            System.out.println("Title: " + title);
            System.out.println("Link: " + link);
            System.out.println("Snippet: " + snippet);
            System.out.println("----------------------------");
        }
    }

    private static String extractValue(String json, String key) {
        int startIndex = json.indexOf(key) + key.length();
        if (startIndex == -1) return "N/A";
        int endIndex = json.indexOf("\"", startIndex);
        return json.substring(startIndex, endIndex).replace("\\n", " ").replace("\\\"", "\"");
    }
}

import java.io.*;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpURLConnectionDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                connection.disconnect();

                JSONArray jsonArray = new JSONArray(response.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String title = jsonObject.getString("title");
                    int price = jsonObject.getInt("price");
                    String thumbnail = jsonObject.getString("thumbnail");

                    System.out.println("Title: " + title);
                    System.out.println("Price: " + price);
                    System.out.println("Thumbnail: " + thumbnail);
                    System.out.println();
                }
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

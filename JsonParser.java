import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
    public static void main(String[] args) {
        String json = "[{\"title\":\"Book 1\",\"price\":10,\"thumbnail\":\"thumbnail1.jpg\"}]";

        try {
            JSONArray jsonArray = new JSONArray(json);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

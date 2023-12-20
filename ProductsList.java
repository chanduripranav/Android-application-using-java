//import java.nio.channels.GatheringByteChannel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductsList {
    public static void main(String[] args) {
           try{ 
        //String jsonString = "";
URL url = new URI("https://dummyjson.com/products").toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder response = new StringBuilder();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
               
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);

                }
            }

     
        String jsonString = response.toString();
        JSONObject data = new JSONObject(jsonString);
        JSONArray jsonArray = data.getJSONArray("products");
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonProduct = jsonArray.getJSONObject(i);

            Product product = new Product(
                    jsonProduct.getString("title"),
                    jsonProduct.getDouble("price"),
                    jsonProduct.getString("thumbnail")
            );
            products.add(product);
        }
        System.out.println(products.size());  
        for (int index = 0; index < products.size(); index++) {
            Product product = products.get(index);
            String output = "<" + (index + 1) + "> Product title: " + product.getTitle() + " price: " + product.getPrice();
            System.out.println(output);
            
        }

        } catch (Exception e) {
            System.out.println("Error occurred:" +e.getMessage());
        }

    }
}

class Product {
    private String title;
    private double price;
    private String thumbnail;

    public Product(String title, double price, String thumbnail) {
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}

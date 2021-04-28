package kg.megacom;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        Main main = new Main();
        Gson gson = new Gson();

        String jayson = main.sendGet().toString();

        List<Person> people = gson.fromJson(jayson, new TypeToken<List<Person>>() {}.getType());

        people.forEach(System.out::println);
    }

    private StringBuilder sendGet() throws Exception {

        String url = "https://jsonplaceholder.typicode.com/posts";

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;


            while ((line = in.readLine()) != null) {
                    response.append(line);
            }
            return response;
        }
    }
}

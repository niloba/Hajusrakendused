import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {

    public static void main(String[] args) {
        String apiKey = "5ffa5bdab1844cccb2ed0bbf9a3e9422";
        String city = "Tallinn"; // Voib panna erinevad linnad

        getWeatherForecast(apiKey, city);
    }

    public static void getWeatherForecast(String apiKey, String city) {
        try {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                StringBuilder responseBuilder = new StringBuilder();

                while (scanner.hasNextLine()) {
                    responseBuilder.append(scanner.nextLine());
                }

                String response = responseBuilder.toString();
                System.out.println(response);

                scanner.close();
                inputStream.close();
            } else {
                System.out.println("Failed to retrieve weather forecast. Response Code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
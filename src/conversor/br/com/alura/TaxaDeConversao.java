package conversor.br.com.alura;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class TaxaDeConversao {
    private static final String API_KEY = "d6b5c5839c66e98f52395bf0";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static double getExchangeRate(String from, String to) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + API_KEY + "/latest/" + from;

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro ao buscar taxas de câmbio: " + response.message());
            }

            String responseBody = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

            return jsonObject.getAsJsonObject("conversion_rates").get(to).getAsDouble();
        }
    }
}

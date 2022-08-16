package dds.miliechi.parcialpractico.apis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

@Slf4j
public class CalculadoraExternaBMI implements CalculadoraBMI {
    @Override
    public double calcular(double alturaEnMetros, double pesoEnKg) throws IOException {
        // https://rapidapi.com/principalapis/api/body-mass-index-bmi-calculator/
        try {
            String url = "https://body-mass-index-bmi-calculator.p.rapidapi.com/metric?weight=" + pesoEnKg +
                    "&height=" + alturaEnMetros;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("X-RapidAPI-Key", "007a17e0edmshdf2eac496ac97b7p178979jsn1bd6cb14e17e")
                    .addHeader("X-RapidAPI-Host", "body-mass-index-bmi-calculator.p.rapidapi.com")
                    .build();
            ResponseBody responseBody = client.newCall(request).execute().body();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseCalculadoraExterna response = objectMapper.readValue(responseBody.string(), ResponseCalculadoraExterna.class);
            return response.getBmi();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ResponseCalculadoraExterna {
        private double bmi;
    }
}

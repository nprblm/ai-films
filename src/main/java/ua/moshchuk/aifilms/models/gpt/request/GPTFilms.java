package ua.moshchuk.aifilms.models.gpt.request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.moshchuk.aifilms.models.entityes.Film;
import ua.moshchuk.aifilms.models.gpt.response.ChatGPTResponse;
import ua.moshchuk.aifilms.service.ChatGPTService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class GPTFilms {

    @Autowired
    private ChatGPTService chatGPTService;

    @Value("${x.rapid.api.key}")
    private String XRapidAPIKey;

    @Value("${x.rapid.api.host}")
    private String XRapidAPIHost;

    public List<Film> getRecommendationFilms(Film film) {
        List<Film> filmsList = new ArrayList<>();

        String prompt = String.format("I like movies: %s. Based on my preferences," +
                " find 5 movies that are similar in genre and plot that I would like and print their name in english," +
                "  year of release and IMDB rating. The output should be in JSON format," +
                " have class films and fields name, year, rating_imdb. name must be string, year must be int and rating_imdb must be float", film.getName());

        ChatGPTResponse chatCPTResponse = chatGPTService.getChatCPTResponse(prompt);

        String jsonString = chatCPTResponse.getChoices().get(0).getMessage().getContent();
        try {
            jsonString = jsonString.substring(jsonString.indexOf("{"), jsonString.lastIndexOf("}") + 1);
            filmsList = fromJSONSTringToList(jsonString);
        } catch (Exception e) {

        }
        return filmsList;
    }

    private List<Film> fromJSONSTringToList(String jsonString) {
        List<Film> resultList = new ArrayList<>();
        final JSONObject obj = new JSONObject(jsonString);
        final JSONArray films = obj.getJSONArray("films");
        for (int i = 0; i < films.length(); ++i) {
            final JSONObject item = films.getJSONObject(i);
            resultList.add(new Film(item.getString("name"), item.getInt("year"), item.getFloat("rating_imdb"), getImageUrl(item.getString("name"), item.getInt("year"))));
        }
        return resultList;
    }

    private String getImageUrl(String filmName, int filmYear) {
        String prompt = String.format("https://imdb8.p.rapidapi.com/auto-complete?q=%s(%d)", filmName.replace(" ", "_"), filmYear);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(prompt))
                    .header("X-RapidAPI-Key", XRapidAPIKey)
                    .header("X-RapidAPI-Host", XRapidAPIHost)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            final JSONObject obj = new JSONObject(responseBody);
            final JSONArray films = obj.getJSONArray("d");
            final JSONObject item = films.getJSONObject(0);
            return item.getJSONObject("i").getString("imageUrl");
        } catch (Exception e) {
            return "Error";
        }
    }

}

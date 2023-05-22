package ua.moshchuk.aifilms.models.gpt.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatGPTResponse {

    public String id;

    public String object;

    public int created;

    public List<Choice> choices;

    public Usage usage;

}

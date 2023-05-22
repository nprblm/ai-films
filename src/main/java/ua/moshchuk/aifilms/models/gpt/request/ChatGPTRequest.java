package ua.moshchuk.aifilms.models.gpt.request;


import lombok.Getter;
import lombok.Setter;
import ua.moshchuk.aifilms.models.gpt.common.Message;

import java.util.List;

@Getter
@Setter
public class ChatGPTRequest {

    private String model;

    private List<Message> messages;

    private Integer max_tokens;

}

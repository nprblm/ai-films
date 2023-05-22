package ua.moshchuk.aifilms.models.gpt.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.moshchuk.aifilms.models.gpt.common.Message;

@Getter
@Setter
@NoArgsConstructor
public class Choice {

    private int index;

    private Message message;

    private String finish_reason;

}

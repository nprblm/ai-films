package ua.moshchuk.aifilms.models.gpt.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Usage {

    private int prompt_tokens;

    private int completion_tokens;

    private int total_tokens;

}

package com.anastasia.trade_project.dto;

import com.anastasia.trade_project.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
public class TelegramChatDto {

    @JsonProperty("chat_id")
    private Long chatId;

    @JsonProperty("user_id")
    private UUID userId;

    private Status status;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "YYYY-MM-DD")
    private String createdAt;


    @Builder
    public TelegramChatDto(Long chatId, UUID userId, Status status, String createdAt) {
        this.chatId = chatId;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public TelegramChatDto() {}
}

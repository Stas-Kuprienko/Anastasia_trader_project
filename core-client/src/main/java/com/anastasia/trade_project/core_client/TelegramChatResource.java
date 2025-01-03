package com.anastasia.trade_project.core_client;

import com.anastasia.trade_project.dto.TelegramChatDto;
import com.anastasia.trade_project.dto.UserDto;
import com.anastasia.trade_project.enums.Language;
import com.anastasia.trade_project.enums.Role;
import org.springframework.web.client.RestClient;
import java.util.Optional;

public class TelegramChatResource extends HttpError404Handler {

    private static final String resourceUrl = "/telegram-chats";

    private final RestClient restClient;


    TelegramChatResource(String baseUrl) {
        baseUrl += resourceUrl;
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }


//    public Optional<TelegramChatDto> findById(long chatId) {
//        return process(() -> restClient.get()
//                .uri(CoreServiceClientV1.uriById(chatId))
//                .retrieve()
//                .body(TelegramChatDto.class));
//    }

    //TODO TEMPORARY SOLUTION !!!!!!!!!!!!!!!!!!!!!!!

    public Optional<TelegramChatDto> findById(long chatId) {
        UserDto user = UserDto.builder()
                .id(1L)
                .name("Stanislav")
                .language(Language.RU)
                .login("example@email.com")
                .role(Role.USER)
                .build();
        return Optional.of(new TelegramChatDto(chatId, user));
    }
}

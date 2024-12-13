package core_client_reactive;

public class CoreServiceClientReactiveV1 {

    public final UsersResource USERS;
    public final TelegramChatResource TELEGRAM_CHAT;
    public final AccountsResource ACCOUNTS;
    public final MarketDataResource MARKET;


    public CoreServiceClientReactiveV1(String baseUrl) {
        baseUrl += "/api/v1";
        USERS = new UsersResource(baseUrl);
        ACCOUNTS = new AccountsResource(baseUrl);
        MARKET = new MarketDataResource(baseUrl);
        TELEGRAM_CHAT = new TelegramChatResource(baseUrl);
    }


    public static String uriById(Object id) {
        return "/%s".formatted(id.toString());
    }
}

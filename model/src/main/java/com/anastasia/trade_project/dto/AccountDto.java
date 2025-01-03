package com.anastasia.trade_project.dto;

import com.anastasia.trade_project.enums.Broker;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
public class AccountDto {

    private UUID id;

    private UserDto user;

    @JsonProperty("client_id")
    private String clientId;

    private Broker broker;

    @JsonProperty("token_expires_at")
    @JsonFormat(pattern = "YYYY-MM-DD")
    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2})")
    private String tokenExpiresAt;

    @JsonProperty("risk_profile")
    private RiskProfileDto riskProfile;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "YYYY-MM-DD")
    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2})")
    private String createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})")
    private String updatedAt;


    @Builder
    public AccountDto(UUID id,
                      UserDto user,
                      String clientId,
                      Broker broker,
                      String tokenExpiresAt,
                      RiskProfileDto riskProfile,
                      String createdAt,
                      String updatedAt) {
        this.id = id;
        this.user = user;
        this.clientId = clientId;
        this.broker = broker;
        this.tokenExpiresAt = tokenExpiresAt;
        this.riskProfile = riskProfile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AccountDto() {}
}

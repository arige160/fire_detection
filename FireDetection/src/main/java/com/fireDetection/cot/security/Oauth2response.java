package com.fireDetection.cot.security;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbVisibility;
import com.fireDetection.cot.FieldPropertyVisibilityStrategy;

@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Oauth2response {
    @JsonbProperty("access_token")
    private String accessToken;

    @JsonbProperty("expires_in")
    private int expiresIn;

    @JsonbProperty("userId")
    private String employeeId;

    @JsonbProperty("refresh_token")
    private String refreshToken;


    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUserId() {
        return employeeId;
    }

    static Oauth2response of(AccessToken accessToken, RefreshToken refreshToken, int expiresIn, String userId) {
        Oauth2response response = new Oauth2response();
        response.accessToken = accessToken.getToken();
        response.refreshToken = refreshToken.getToken();
        response.employeeId = userId;
        response.expiresIn = expiresIn;
        return response;
    }
}
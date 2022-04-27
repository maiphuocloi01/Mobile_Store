package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Token implements Serializable {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("error")
    private String error;
    @SerializedName("error_description")
    private String errorDescription;

    public Token(String accessToken, String tokenType, int expiresIn, String error, String errorDescription) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}

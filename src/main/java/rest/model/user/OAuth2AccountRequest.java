package rest.model.user;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class OAuth2AccountRequest {
    public static final String GOOGLE_PLUS = "google_plus";
    public static final String FACEBOOK = "facebook";

    @NotBlank
    private String accessToken;
    @NotBlank
    private String provider;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "OAuth2AccountRequest{" +
                "accessToken='" + accessToken + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}

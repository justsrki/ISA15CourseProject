package rest.user;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class AuthenticationResponse {
    private String role;
    private Integer userId;
    private String accessToken;

    public AuthenticationResponse(String role, Integer userId, String accessToken) {
        this.role = role;
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

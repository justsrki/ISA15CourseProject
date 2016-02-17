package model.oauth;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("Unused")
public class GoogleTokenInfo {
    private String azp;
    private String aud;
    private String sub;
    private String scope;
    private Integer exp;
    private Integer expires_in;
    private String email;
    private String email_verified;
    private String access_type;

    public String getAzp() {
        return azp;
    }

    public void setAzp(String azp) {
        this.azp = azp;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(String email_verified) {
        this.email_verified = email_verified;
    }

    public String getAccess_type() {
        return access_type;
    }

    public void setAccess_type(String access_type) {
        this.access_type = access_type;
    }

    @Override
    public String toString() {
        return "GoogleTokenInfo{" +
                "azp='" + azp + '\'' +
                ", aud='" + aud + '\'' +
                ", sub='" + sub + '\'' +
                ", scope='" + scope + '\'' +
                ", exp=" + exp +
                ", expires_in=" + expires_in +
                ", email='" + email + '\'' +
                ", email_verified='" + email_verified + '\'' +
                ", access_type='" + access_type + '\'' +
                '}';
    }

}
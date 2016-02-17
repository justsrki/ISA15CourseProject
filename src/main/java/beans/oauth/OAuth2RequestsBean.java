package beans.oauth;

import model.oauth.FacebookUserInfo;
import model.oauth.GoogleTokenInfo;
import model.oauth.GoogleUserInfo;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.ejb.Stateless;
import java.io.IOException;

/**
 * @author - Srđan Milaković
 */
@Stateless
@SuppressWarnings("unused")
public class OAuth2RequestsBean implements OAuth2RequestsLocal {
    private static final String TOKEN_INFO_URL = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=%s";
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=%s";

    // https://developers.facebook.com/docs/facebook-login/permissions#reference-email
    private static final String FACEBOOK_USER_INFO_URL = "https://graph.facebook.com/me?fields=email,first_name,last_name&access_token=%s";

    @Override
    public GoogleTokenInfo googleTokenInfo(String accessToken) {
        String url = String.format(TOKEN_INFO_URL, accessToken);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet getRequest = new HttpGet(url);
            HttpResponse response = httpClient.execute(getRequest);
            return new Gson().fromJson(EntityUtils.toString(response.getEntity(), "UTF-8"), GoogleTokenInfo.class);
        } catch (IOException e) {
            System.err.println("Can't send a request: " + url);
            return null;
        }
    }

    @Override
    public GoogleUserInfo googleUserInfo(String accessToken) {
        String url = String.format(USER_INFO_URL, accessToken);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet getRequest = new HttpGet(url);
            HttpResponse response = httpClient.execute(getRequest);
            return new Gson().fromJson(EntityUtils.toString(response.getEntity(), "UTF-8"), GoogleUserInfo.class);
        } catch (IOException e) {
            System.err.println("Can't send a request: " + url);
            return null;
        }
    }

    @Override
    public FacebookUserInfo facebookUserInfo(String accessToken) {
        String url = String.format(FACEBOOK_USER_INFO_URL, accessToken);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet getRequest = new HttpGet(url);
            HttpResponse response = httpClient.execute(getRequest);
            return new Gson().fromJson(EntityUtils.toString(response.getEntity(), "UTF-8"), FacebookUserInfo.class);
        } catch (IOException e) {
            System.err.println("Can't send a request: " + url);
        }

        return null;
    }
}

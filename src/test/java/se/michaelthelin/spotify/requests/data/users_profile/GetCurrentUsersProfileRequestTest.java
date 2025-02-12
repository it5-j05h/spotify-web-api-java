package se.michaelthelin.spotify.requests.data.users_profile;

import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ProductType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetCurrentUsersProfileRequestTest extends AbstractDataTest<User> {
  private final GetCurrentUsersProfileRequest defaultRequest = ITest.SPOTIFY_API
    .getCurrentUsersProfile()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/users_profile/GetCurrentUsersProfileRequest.json"))
    .build();

  public GetCurrentUsersProfileRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    Assert.assertEquals(
      "https://api.spotify.com:443/v1/me",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final User user) {
    assertEquals(
      "1937-06-01",
      user.getBirthdate());
    assertEquals(
      CountryCode.SE,
      user.getCountry());
    assertEquals(
      "JM Wizzler",
      user.getDisplayName());
    assertEquals(
      "email@example.com",
      user.getEmail());
    assertNotNull(
      user.getExternalUrls());
    assertNotNull(
      user.getFollowers());
    assertEquals(
      "https://api.spotify.com/v1/users/wizzler",
      user.getHref());
    assertEquals(
      "wizzler",
      user.getId());
    assertEquals(
      1,
      user.getImages().length);
    Assert.assertEquals(
      ProductType.PREMIUM,
      user.getProduct());
    Assert.assertEquals(
      ModelObjectType.USER,
      user.getType());
    assertEquals(
      "spotify:user:wizzler",
      user.getUri());
  }
}

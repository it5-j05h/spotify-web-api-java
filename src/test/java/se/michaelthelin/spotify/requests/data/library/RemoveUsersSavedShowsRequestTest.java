package se.michaelthelin.spotify.requests.data.library;

import org.apache.hc.core5.http.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Assertions;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class RemoveUsersSavedShowsRequestTest extends AbstractDataTest<String> {
  private final RemoveUsersSavedShowsRequest defaultRequest = ITest.SPOTIFY_API
    .removeUsersSavedShows(ITest.ID_SHOW, ITest.ID_SHOW)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .market(ITest.MARKET)
    .build();
  private final RemoveUsersSavedShowsRequest bodyRequest = ITest.SPOTIFY_API
    .removeUsersSavedShows(ITest.SHOWS)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .market(ITest.MARKET)
    .build();

  public RemoveUsersSavedShowsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    Assert.assertEquals(
      "https://api.spotify.com:443/v1/me/shows?ids=5AvwZVawapvyhJUIx71pdJ%2C5AvwZVawapvyhJUIx71pdJ&market=SE",
      defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    Assertions.assertHasHeader(defaultRequest, "Content-Type", "application/json");
    Assertions.assertHasBodyParameter(bodyRequest,
      "ids",
      ITest.SHOWS);
    Assert.assertEquals("https://api.spotify.com:443/v1/me/shows?market=SE",
      bodyRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
      string);
  }
}

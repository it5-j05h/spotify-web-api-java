package se.michaelthelin.spotify.requests.data.playlists;

import com.google.gson.Gson;
import org.apache.hc.core5.http.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Assertions;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddItemsToPlaylistRequestTest extends AbstractDataTest<SnapshotResult> {
  private final AddItemsToPlaylistRequest defaultRequest = ITest.SPOTIFY_API
    .addItemsToPlaylist(ITest.ID_PLAYLIST, new Gson().fromJson(ITest.URIS, String[].class))
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/AddItemsToPlaylistRequest.json"))
    .position(ITest.POSITION)
    .build();
  private final AddItemsToPlaylistRequest bodyRequest = ITest.SPOTIFY_API
    .addItemsToPlaylist(ITest.ID_PLAYLIST, ITest.URIS)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/AddItemsToPlaylistRequest.json"))
    .position(ITest.POSITION, true)
    .build();

  public AddItemsToPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    Assert.assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?uris=spotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX%2Cspotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX&position=0",
      defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    Assertions.assertHasHeader(defaultRequest, "Content-Type", "application/json");
    Assertions.assertHasBodyParameter(
      bodyRequest,
      "uris",
      ITest.URIS);
    Assertions.assertHasBodyParameter(
      bodyRequest,
      "position",
      ITest.POSITION);
    Assert.assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks",
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

  public void shouldReturnDefault(final SnapshotResult snapshotResult) {
    assertEquals(
      "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+",
      snapshotResult.getSnapshotId());
  }
}

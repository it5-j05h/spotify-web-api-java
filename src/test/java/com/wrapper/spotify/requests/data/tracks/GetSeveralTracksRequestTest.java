package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetSeveralTracksRequestTest implements ITest<Track[]> {
  private final GetSeveralTracksRequest successRequest = SPOTIFY_API
          .getSeveralTracks("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/tracks/GetSeveralTracksRequest.json"))
          .build();

  public GetSeveralTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Track[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Track[] tracks) {
    assertEquals(
            2,
            tracks.length);
  }
}

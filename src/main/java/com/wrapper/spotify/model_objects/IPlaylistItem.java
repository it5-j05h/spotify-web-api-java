package com.wrapper.spotify.model_objects;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Episode;
import com.wrapper.spotify.model_objects.specification.ExternalUrl;
import com.wrapper.spotify.model_objects.specification.Track;


/**
 * This interface represents objects returned by the API that can be played, saved in a playlist, etc,
 * currently {@link Episode} and {@link Track}.
 */
public interface IPlaylistItem extends IModelObject {

  /**
   * Get a URL to a 30 second preview (MP3 format) of this playlist item. {@code null} if not available.
   *
   * @return A URL to an audio preview.
   */
  public String getAudioPreviewUrl();
  
  /**
   * Get the duration of this playlist item in milliseconds.
   *
   * @return The playlist item length in milliseconds.
   */
  public Integer getDurationMs();
  
  
  /**
   * Check whether the playlist item is explicit or not.
   *
   * @return Whether or not the playlist item has explicit lyrics ({@code true} = yes it does; {@code false} = no it does not
   * <b>OR</b> unknown).
   */
  public Boolean getIsExplicit();
  
  /**
   * Get the external URLs of the playlist item.<br>
   * Example: Spotify-URL.
   *
   * @return Known external URLs for this playlist item.
   */
  public ExternalUrl getExternalUrls();
  
  /**
   * Get the full Spotify Web API endpoint URL of the playlist item.
   *
   * @return A link to the Web API endpoint providing full details of the playlist item.
   */
  public String getHref();
  
  /**
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify ID</a> of the
   * playlist item.
   *
   * @return The Spotify ID for the playlist item.
   */
  public String getId();
  
  /**
   * Check whether the playlist item is playable in the market, which may has been specified somewhere before requesting it.
   *
   * @return If {@code true}, the playlist item is playable in the given market. Otherwise {@code false}.
   */
  public Boolean getIsPlayable();
  
  /**
   * Get the name of the playlist item.
   *
   * @return playlist item name.
   */
  public String getName();
  
  /**
   * Get the type of the IPlaylistItem.
   * Possible values: {@code ModelObjectType.TRACK} or {@code ModelObjectType.EPISODE}
   *
   * @return The type of the IPlaylistItem.
   */
  ModelObjectType getType();
  
  /**
   * Get the Spotify playlist item URI.
   *
   * @return The <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a> for
   * the playlist item.
   */
  public String getUri();
}

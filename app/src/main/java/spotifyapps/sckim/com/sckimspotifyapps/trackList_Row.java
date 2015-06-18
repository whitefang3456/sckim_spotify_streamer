package spotifyapps.sckim.com.sckimspotifyapps;

/**
 * Created by SC Kim on 6/19/2015.
 */
public class trackList_Row {
    private int imageId;
    private String track_title;
    private String album_name;

    public trackList_Row(int imageId, String albumName, String trackTitle) {
        this.imageId = imageId;
        this.album_name = albumName;
        this.track_title = trackTitle;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getTrackTitle() {
        return track_title;
    }
    public void setTrackTitle(String trackTitle) {
        this.track_title = trackTitle;
    }
    public String getAlbumName() {
        return album_name;
    }
    public void setAlbumName(String albumName) {
        this.album_name = albumName;
    }
    @Override
    public String toString() {
        return album_name + "\n" + track_title;
    }
}

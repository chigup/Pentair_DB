package pentair.pentair_db;

public class artist {

    String artistName;
    String artistId;
    String artistGenre;

    public  artist(){

    }

    public artist(String artistName, String atrtistId, String artistGenre) {
        this.artistName = artistName;
        this.artistId = atrtistId;
        this.artistGenre = artistGenre;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistGenre() {
        return artistGenre;
    }
}

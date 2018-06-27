package pentair.pentair_db;

public class Track  {

String trackName;
String trackId;
int trackRating;


    public  void Track(){

    }

    public Track(String trackName, String trackId, int trackRating) {
        this.trackName = trackName;
        this.trackId = trackId;
        this.trackRating = trackRating;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackId() {
        return trackId;
    }

    public int getTrackRating() {
        return trackRating;
    }
}

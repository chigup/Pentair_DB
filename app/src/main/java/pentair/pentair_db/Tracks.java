package pentair.pentair_db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Tracks extends AppCompatActivity {


    EditText track;
    TextView artistName;
    SeekBar rating;
    ListView listViewTrack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);



        track =findViewById(R.id.editTrackName);
        artistName =findViewById(R.id.textView);
        rating = findViewById(R.id.rating);
        listViewTrack = findViewById(R.id.listViewTrack);
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);
        String id = intent.getStringExtra(MainActivity.ARTIST_ID);
        artistName.setText(name);




    }
}

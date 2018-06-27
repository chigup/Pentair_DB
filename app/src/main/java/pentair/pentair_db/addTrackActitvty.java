package pentair.pentair_db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addTrackActitvty extends AppCompatActivity {


    EditText track;
    TextView artistName;
    SeekBar rating;
    Button addTrack;
    ListView listViewTrack;
    FirebaseDatabase trackDataBase;
    DatabaseReference trackRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);


        addTrack = findViewById((R.id.addTrack));
        track =findViewById(R.id.editTrackName);
        artistName =findViewById(R.id.textView);
        rating = findViewById(R.id.rating);
        listViewTrack = findViewById(R.id.listViewTrack);


        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);
        String id = intent.getStringExtra(MainActivity.ARTIST_ID);
        artistName.setText(name);
        trackDataBase = FirebaseDatabase.getInstance();
        trackRef = trackDataBase.getReference("TRAKCS").child(id);

        addTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTrack();
            }
        });

    }


    private void saveTrack(){

        String trackName = track.getText().toString().trim();
        int rat = rating.getProgress();
        if(!TextUtils.isEmpty(trackName)){

            String id = trackRef.push().getKey();
            Track track1 = new Track(trackName,id,rat);
            trackRef.child(id).setValue(track1);
            Toast.makeText(this, "song added", Toast.LENGTH_SHORT).show();



        }else{
            Toast.makeText(this, "enter song", Toast.LENGTH_SHORT).show();
        }
    }

}

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class addTrackActitvty extends AppCompatActivity {


    EditText track;
    TextView artistName;
    SeekBar rating;
    Button addTrack;
    ListView listViewTrack;
    FirebaseDatabase trackDataBase;
    DatabaseReference trackRef;
    List<Track> trackList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);


        addTrack = findViewById((R.id.addTrack));
        track =findViewById(R.id.editTrackName);
        artistName =findViewById(R.id.textView);
        rating = findViewById(R.id.rating);
        listViewTrack = findViewById(R.id.listViewTrack);
        trackList = new ArrayList<>();

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

    @Override
    protected void onStart() {
        super.onStart();
        trackRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trackList.clear();
                for (DataSnapshot trackSnapShot : dataSnapshot.getChildren()){
                    Track track = trackSnapShot.getValue(Track.class);
                    trackList.add(track);
                    track_list_adapter adapter = new track_list_adapter(addTrackActitvty.this,trackList);
                    listViewTrack.setAdapter((adapter));


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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





package pentair.pentair_db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String  ARTIST_NAME ="ArtistName";
    public static final String  ARTIST_ID ="ArtistId";

    EditText editText;
    Spinner spinner;
    Button addArtist;
    ListView artistListView;
    DatabaseReference ref;
    FirebaseDatabase database;
    List<artist> artistlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         database = FirebaseDatabase.getInstance();
         ref = database.getReference("artist");
         editText = findViewById(R.id.editName);
         spinner = findViewById((R.id.genre));
         artistListView = findViewById((R.id.listViewArtist));
         addArtist = findViewById((R.id.addArtist));
         artistlist = new ArrayList< >();
         addArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add();

            }
        });
   artistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           artist artist = artistlist.get(i);
           Intent intent =  new Intent(getApplicationContext(),addTrackActitvty.class);
           intent.putExtra(ARTIST_NAME,artist.getArtistName());
           intent.putExtra(ARTIST_ID,artist.getArtistId());
           startActivity(intent);

       }
   });






    }







    private void add()
    {
        String name = editText.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();
        if(!TextUtils.isEmpty(name)){

            String id = ref.push().getKey();
            artist artist1 = new artist(id,name,genre);
            ref.child(id).setValue(artist1);
            Toast.makeText(this, "artist added", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this,"fill name",Toast.LENGTH_SHORT).show();


        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistlist.clear();
                    for (DataSnapshot artistSnapShot: dataSnapshot.getChildren()){
                        artist artist = artistSnapShot.getValue(artist.class);
                        artistlist.add(artist);
                        artist_list_adapter adapter = new artist_list_adapter(MainActivity.this, artistlist);
                        artistListView.setAdapter((adapter));


                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}











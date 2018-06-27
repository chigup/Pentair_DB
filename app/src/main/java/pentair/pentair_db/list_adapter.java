package pentair.pentair_db;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class list_adapter extends ArrayAdapter {

    private Activity context;
    private List<artist> artistList;

    public  list_adapter(Activity context, List<artist> artistList){

        super(context,R.layout.list_layout,artistList);
        this.context = context;
        this.artistList = artistList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View layoutView = inflater.inflate(R.layout.list_layout,null,true);
        TextView name = (TextView)layoutView.findViewById(R.id.textViewArtist);
        TextView genre = (TextView)layoutView.findViewById(R.id.textViewGenre);
        artist artist  = artistList.get(position);
        name.setText(artist.getArtistName());
        genre.setText(artist.getArtistGenre());
        return  layoutView;
    }
}

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

public class track_list_adapter extends ArrayAdapter<Track>{




        private Activity context;
        private List<Track> trackList;

        public track_list_adapter(Activity context, List<Track> trackList){

            super(context,R.layout.track_list_layout,trackList);
            this.context = context;
            this.trackList = trackList;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View layoutView = inflater.inflate(R.layout.track_list_layout,null,true);
            TextView name = (TextView)layoutView.findViewById(R.id.textViewTrack);
            TextView genre = (TextView)layoutView.findViewById(R.id.textViewTrackRating);
            Track track  = trackList.get(position);
            name.setText(track.getTrackName());
            genre.setText(String.valueOf(track.getTrackRating()));
            return  layoutView;
        }

}

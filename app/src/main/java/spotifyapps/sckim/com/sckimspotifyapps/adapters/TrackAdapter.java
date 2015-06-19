package spotifyapps.sckim.com.sckimspotifyapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kaaes.spotify.webapi.android.models.Track;
import spotifyapps.sckim.com.sckimspotifyapps.R;

/**
 * Created by SC Kim on 6/20/2015.
 */
public class TrackAdapter extends ArrayAdapter<Track>{

    Context mContext;
    Track item;
    public TrackAdapter(Context context, int resource) {
        super(context, resource);
        this.mContext = context;
        this.item = item;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        view = inflater.inflate(R.layout.tracklist,null);

        TextView songnameText = (TextView) view.findViewById(R.id.list_track_title);
        TextView albumnameText = (TextView) view.findViewById(R.id.list_album_name);
        ImageView coverImageview = (ImageView) view.findViewById(R.id.image_track_album);

        Track item = getItem(position);
        songnameText.setText(item.name);
        albumnameText.setText(item.album.name);
        if(item.album.images.size()!=0){
            Picasso.with(mContext).load(item.album.images.get(0).url).resize(200,200)
                    .placeholder(mContext.getResources().getDrawable(R.drawable.ic_track_icon)).into(coverImageview);
        }

        return view;
    }


}

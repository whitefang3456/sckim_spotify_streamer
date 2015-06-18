package spotifyapps.sckim.com.sckimspotifyapps.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import spotifyapps.sckim.com.sckimspotifyapps.R;
import spotifyapps.sckim.com.sckimspotifyapps.trackList_Row;

/**
 * Created by SC Kim on 6/19/2015.
 */
public class trackListViewAdapter extends ArrayAdapter<trackList_Row> {

    Context context;
    public trackListViewAdapter(Context context, int resourceId,
                                 List<trackList_Row> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtAlbum;
        TextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        trackList_Row rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tracklist_item, null);
            holder = new ViewHolder();
            holder.txtAlbum = (TextView) convertView.findViewById(R.id.list_album_name);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.list_track_title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_artist_album);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtAlbum.setText(rowItem.getAlbumName());
        holder.txtTitle.setText(rowItem.getTrackTitle());
        holder.imageView.setImageResource(rowItem.getImageId());

        return convertView;
    }
}
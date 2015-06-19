package spotifyapps.sckim.com.sckimspotifyapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kaaes.spotify.webapi.android.models.Artist;
import spotifyapps.sckim.com.sckimspotifyapps.R;


/**
 * Created by SC Kim on 6/19/2015.
 */
public class ArtistAdapter extends ArrayAdapter<Artist> {

//    Context mContext;
//    Artist item;
//    public trackListViewAdapter(Context context, int resource) {
//        super(context, resource);
//        this.mContext = context;
//        this.item = item;
//
//    }
//
//    private class ViewHolder {
//        ImageView imageView;
//        TextView txtAlbum;
//        TextView txtTitle;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
////        trackList_Row rowItem = getItem(position);
//        Artist item = getItem(position);
//
//        LayoutInflater mInflater = (LayoutInflater) mContext
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.tracklist_item, null);
//            holder = new ViewHolder();
//            holder.txtAlbum = (TextView) convertView.findViewById(R.id.list_album_name);
//            holder.txtTitle = (TextView) convertView.findViewById(R.id.list_track_title);
//            holder.imageView = (ImageView) convertView.findViewById(R.id.list_artist_album);
//            convertView.setTag(holder);
//        } else
//            holder = (ViewHolder) convertView.getTag();
//
//        holder.txtAlbum.setText(rowItem.getAlbumName());
//        holder.txtTitle.setText(rowItem.getTrackTitle());
//        Picasso.with(mContext).load(item.images.get(0).url).resize(200,200)
//                .placeholder(mContext.getResources().getDrawable(R.drawable.ic_launcher)).into(holder.imageView);
//        return convertView;
//    }



    Context mContext;
    Artist item;
    public ArtistAdapter(Context context, int resource) {
        super(context, resource);
        this.mContext = context;
        this.item = item;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        view = inflater.inflate(R.layout.search_result,null);

        TextView nameText = (TextView) view.findViewById(R.id.textview_artist_name);
        ImageView coverImageImageview = (ImageView) view.findViewById(R.id.image_artist);

        Artist item = getItem(position);
        nameText.setText(item.name);
        if(item.images.size()!=0){
            Picasso.with(mContext).load(item.images.get(0).url).resize(200,200)
                    .placeholder(mContext.getResources().getDrawable(R.drawable.ic_launcher)).into(coverImageImageview);
        }

        return view;
    }


}
package spotifyapps.sckim.com.sckimspotifyapps;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Tracks;
import spotifyapps.sckim.com.sckimspotifyapps.adapters.TrackAdapter;


public class TrackActivity extends ActionBarActivity {

    ListView listView;
    TrackAdapter adapter;

//    public static final String[] albumNames = new String[]{"Korean PSY",
//            "FFVII", "Digimon", "Digimon"};
//
//    public static final String[] trackTitle = new String[]{
//            "G Style",
//            "Final Requiem", "Evolution",
//            "Butterfly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        Intent intent = this.getIntent();
        if (intent != null && intent.hasExtra("ID")) {
            getSupportActionBar().setSubtitle(intent.getStringExtra("NAME"));
            String ID = intent.getStringExtra("ID");
            new populateTopTenTracks(getApplicationContext()).execute(ID);

        }

        ListView listView = (ListView) findViewById(R.id.listview_tracklist);
        adapter = new TrackAdapter(getApplicationContext(), R.layout.tracklist);
        listView.setAdapter(adapter);

    }

    public class populateTopTenTracks extends AsyncTask<String, Void, Tracks> {

        public Context mContext;

        public populateTopTenTracks(Context context){
            this.mContext = context;
        }

        @Override
        protected Tracks doInBackground(String... ID) {

            if(ID[0].equals("")){
                return null;
            }
            else{
                SpotifyApi api = new SpotifyApi();
                SpotifyService spotify = api.getService();
                Log.v("NJ", ID[0]);
                HashMap hm = new HashMap();
                hm.put("country","US");
                Tracks tracks = spotify.getArtistTopTrack(ID[0],hm);
//                int total = tracks.tracks.size();
//                if(total!=0){
//                    for(int i=0;i<total;i++){
//                        Log.v("test", "found  " + tracks.tracks.get(i).name);
//
//                    }
//                }
                return tracks;

            }

        }

        @Override
        protected void onPostExecute(Tracks tracks) {


            if(tracks!=null){
                int total = tracks.tracks.size();
                adapter.clear();

                for(int i=0;i<total;i++){
                    //Log.v("test", "adding  "+tracks.tracks.get(i).name);
                    adapter.add(tracks.tracks.get(i));
                    //Log.v("test", "added "+tracks.tracks.get(i).name);
                }
            }
            else{
                adapter.clear();
                Toast.makeText(mContext, "NO SONG", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

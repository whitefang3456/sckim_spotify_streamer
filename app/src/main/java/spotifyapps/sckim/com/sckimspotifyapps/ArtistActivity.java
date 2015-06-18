package spotifyapps.sckim.com.sckimspotifyapps;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import spotifyapps.sckim.com.sckimspotifyapps.adapters.trackListViewAdapter;


public class ArtistActivity extends ActionBarActivity {

    ListView listView;
    List<trackList_Row> rowItems;

    public static final String[] albumNames = new String[]{"Korean PSY",
            "FFVII", "Digimon", "Digimon"};

    public static final String[] trackTitle = new String[]{
            "G Style",
            "Final Requiem", "Evolution",
            "Butterfly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        updateTrackList();
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

    private void updateTrackList() {

        rowItems = new ArrayList<trackList_Row>();
        for (int i = 0; i < trackTitle.length; i++) {
            trackList_Row item = new trackList_Row(R.drawable.ic_track_icon, albumNames[i], trackTitle[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.artist_tracklist);
        trackListViewAdapter adapter = new trackListViewAdapter(this,
                R.layout.tracklist_item, rowItems);
        listView.setAdapter(adapter);

    }

}

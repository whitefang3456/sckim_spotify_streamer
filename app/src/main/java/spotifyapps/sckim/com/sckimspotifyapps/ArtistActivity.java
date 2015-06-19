package spotifyapps.sckim.com.sckimspotifyapps;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import spotifyapps.sckim.com.sckimspotifyapps.adapters.ArtistAdapter;


public class ArtistActivity extends ActionBarActivity {

    ArtistAdapter adapter;
    EditText editText;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("QUERY",editText.getText().toString());
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            editText.setText(savedInstanceState.getString("QUERY"));
        }
        setContentView(R.layout.activity_artist);


        editText = (EditText) findViewById(R.id.searchTextBox);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String query = editText.getText().toString();
                new SearchFunction(getApplicationContext()).execute(query);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        ListView listView = (ListView) findViewById(R.id.artist_listView);
        adapter = new ArtistAdapter(getApplicationContext(),R.layout.search_result);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), TrackActivity.class)
                        .putExtra("ID", adapter.getItem(position).id);
                intent.putExtra("NAME", adapter.getItem(position).name);
                startActivity(intent);
            }
        });

    }

    public class SearchFunction  extends AsyncTask<String, Void, ArtistsPager> {

        Context mContext;

        public SearchFunction(Context context){
            this.mContext = context;
        }

        @Override
        protected ArtistsPager doInBackground(String... keyword) {

            if(keyword[0].equals("")){
                return null;
            }
            else{
                SpotifyApi api = new SpotifyApi();
                SpotifyService spotify = api.getService();
                ArtistsPager artistsPage = spotify.searchArtists(keyword[0]);

//                int total = artistsPage.artists.items.size();
//                if(total!=0){
//                    for(int i=0;i<total;i++){
//                        Log.v("test", "found  " + artistsPage.artists.items.get(i).name);
//
//                    }
//                }
                return artistsPage;

            }

        }

        @Override
        protected void onPostExecute(ArtistsPager artistsPager) {

            if(artistsPager!=null){
                int total = artistsPager.artists.items.size();
                adapter.clear();

                for(int i=0;i<total;i++){
                    // Log.v("test", "adding  "+artistsPager.artists.items.get(i).name);
                    adapter.add(artistsPager.artists.items.get(i));
                    // Log.v("test", "added "+artistsPager.artists.items.get(i).name);
                }
            }
            else{
                adapter.clear();
                Toast.makeText(mContext, "NO QUERY", Toast.LENGTH_SHORT).show();
            }

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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





package spotifyapps.sckim.com.sckimspotifyapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Player;


public class LoginActivity extends Activity {

    // TODO: Replace with your client ID
    private static final String CLIENT_ID = "33906dac9c694a83915eba4a4f6bce08";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "sckim-spotify-test://callback";

    private Player mPlayer;
    private static final int REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_spotify);

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
//                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
//                mPlayer = Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
//                    @Override
//                    public void onInitialized(Player player) {
//                        mPlayer.addConnectionStateCallback(LoginActivity.this);
//                        mPlayer.addPlayerNotificationCallback(LoginActivity.this);
//                        mPlayer.play("spotify:track:2TpxZ7JUBn3uw46aR7qd6V");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
//                    }
//                });
                Intent intentToHome = new Intent(this.getApplicationContext(), HomeActivity.class);
                startActivity(intentToHome);
            }
        }
    }


//    @Override
//    public void onLoggedIn() {
//        Log.d("MainActivity", "User logged in");
//    }
//
//    @Override
//    public void onLoggedOut() {
//        Log.d("MainActivity", "User logged out");
//    }
//
//    @Override
//    public void onLoginFailed(Throwable error) {
//        Log.d("MainActivity", "Login failed");
//    }
//
//    @Override
//    public void onTemporaryError() {
//        Log.d("MainActivity", "Temporary error occurred");
//    }
//
//    @Override
//    public void onConnectionMessage(String message) {
//        Log.d("MainActivity", "Received connection message: " + message);
//    }
//
//    @Override
//    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {
//        Log.d("MainActivity", "Playback event received: " + eventType.name());
//    }
//
//    @Override
//    public void onPlaybackError(ErrorType errorType, String errorDetails) {
//        Log.d("MainActivity", "Playback error received: " + errorType.name());
//    }
//
//    @Override
//    protected void onDestroy() {
//        Spotify.destroyPlayer(this);
//        super.onDestroy();
//    }


}
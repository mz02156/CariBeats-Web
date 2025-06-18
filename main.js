console.log("CariBeats website loaded.");

function loginWithSpotify() {
    const client_id = 'f7875920cd894ddf93b4bbc32bb2fb8a';
    const redirect_uri = 'https://mz02156.github.io/CariBeats-Web/';
    const scope = 'streaming user-read-email user-read-private user-library-read user-read-playback-state user-modify-playback-state';

    const auth_url = `https://accounts.spotify.com/authorize?client_id=${client_id}&response_type=token&redirect_uri=${encodeURIComponent(redirect_uri)}&scope=${encodeURIComponent(scope)}`;
    window.location.href = auth_url;
}

function getAccessTokenFromUrl() {
    const hash = window.location.hash.substring(1);
    const params = new URLSearchParams(hash);
    return params.get('access_token');
}

const token = getAccessTokenFromUrl();
if (token) {
    console.log("Spotify Token: ", token);
    localStorage.setItem("spotify_access_token", token);
}

window.onSpotifyWebPlaybackSDKReady = () => {
    const token = localStorage.getItem("spotify_access_token");
    const player = new Spotify.Player({
        name: 'CariBeats Web Player',
        getOAuthToken: cb => { cb(token); },
        volume: 0.8
    });

    player.addListener('initialization_error', ({ message }) => { console.error(message); });
    player.addListener('authentication_error', ({ message }) => { console.error(message); });
    player.addListener('account_error', ({ message }) => { console.error(message); });
    player.addListener('playback_error', ({ message }) => { console.error(message); });

    player.addListener('player_state_changed', state => { console.log(state); });

    player.addListener('ready', ({ device_id }) => {
        console.log('Ready with Device ID', device_id);
        localStorage.setItem("spotify_device_id", device_id);
    });

    player.connect();
};
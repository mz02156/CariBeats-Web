
console.log("CariBeats website loaded.");

function toggleTheme() {
    document.body.classList.toggle("dark-mode");
}

async function loadGenreSongs() {
    const songs = await DatasetLoader.loadSongs();
    StaticSongStore.setSongs(songs);
    displaySongList(songs);
}

function displaySongList(songs) {
    const container = document.getElementById('songList');
    container.innerHTML = '';
    songs.forEach(song => {
        const div = document.createElement('div');
        div.style.background = '#1B1B1B';
        div.style.padding = '12px';
        div.style.marginBottom = '8px';
        div.innerHTML = `
            <p style="font-size:18px; font-weight:bold;">${song.title}</p>
            <p style="color:#BBBBBB;">${song.artists}</p>
            <p style="font-size:12px; color:#AAAAAA;">Tempo: ${song.tempo} BPM</p>
            <p style="font-style:italic; color:#FFEB3B;">Mood: ${song.mood}</p>
        `;
        container.appendChild(div);
    });
}

// Toggle Filters Function
function toggleFilters() {
    const spinner = document.getElementById('genreSpinner');
    if (spinner.style.display === 'none') {
        spinner.style.display = 'block';
    } else {
        spinner.style.display = 'none';
    }
}

// Spotify Login Function
function loginWithSpotify() {
    const client_id = 'f7875920cd894ddf93b4bbc32bb2fb8a';
    const redirect_uri = 'https://mz02156.github.io/CariBeats-Web/';
    const scopes = 'streaming user-read-email user-read-private user-library-read user-read-playback-state user-modify-playback-state';

    const auth_url = `https://accounts.spotify.com/authorize?client_id=${client_id}&response_type=token&redirect_uri=${encodeURIComponent(redirect_uri)}&scope=${encodeURIComponent(scopes)}`;

    window.location.href = auth_url;
}

// Extract Spotify token from URL hash
function extractSpotifyToken() {
    const hash = window.location.hash.substring(1);
    const params = new URLSearchParams(hash);
    const token = params.get('access_token');

    if (token) {
        console.log("Spotify Access Token:", token);
        localStorage.setItem('spotify_access_token', token);
        window.history.replaceState(null, null, window.location.pathname);
    }
}

extractSpotifyToken();

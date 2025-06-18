
document.addEventListener("DOMContentLoaded", async () => {
    console.log("CariBeats website loaded.");

    const songs = await DatasetLoader.loadSongs();
    let filteredSongs = [...songs];

    displaySongs(filteredSongs);

    document.getElementById("applyFiltersButton").addEventListener("click", () => {
        filteredSongs = applyFilters(songs);
        displaySongs(filteredSongs);
    });
});

function displaySongs(songList) {
    const container = document.getElementById("songList");
    container.innerHTML = '';

    songList.forEach(song => {
        const div = document.createElement("div");
        div.className = "song-item";
        div.textContent = `${song.title} - ${song.artists} (${song.genre}) [${song.tempo} BPM]`;
        container.appendChild(div);
    });
}

function applyFilters(songs) {
    const selectedGenre = document.getElementById("genreDropdown").value;
    const minTempo = parseFloat(document.getElementById("minTempo").value) || 0;
    const maxTempo = parseFloat(document.getElementById("maxTempo").value) || 999;

    return songs.filter(song => {
        const matchesGenre = selectedGenre === "All" || song.genre.toLowerCase() === selectedGenre.toLowerCase();
        const matchesTempo = song.tempo >= minTempo && song.tempo <= maxTempo;
        return matchesGenre && matchesTempo;
    });
}

function loginWithSpotify() {
    const client_id = 'f7875920cd894ddf93b4bbc32bb2fb8a';
    const redirect_uri = 'https://mz02156.github.io/CariBeats-Web/';
    const scope = 'streaming user-read-email user-read-private user-library-read user-read-playback-state user-modify-playback-state';

    const auth_url = `https://accounts.spotify.com/authorize?client_id=${client_id}&response_type=token&redirect_uri=${encodeURIComponent(redirect_uri)}&scope=${encodeURIComponent(scope)}`;
    window.location.href = auth_url;
}

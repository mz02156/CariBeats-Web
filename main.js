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

    const minPopularity = parseInt(document.getElementById("minPopularity").value) || 0;
    const maxPopularity = parseInt(document.getElementById("maxPopularity").value) || 100;

    const minDuration = parseInt(document.getElementById("minDuration").value) || 0;
    const maxDuration = parseInt(document.getElementById("maxDuration").value) || 9999999;

    const explicitFilter = document.getElementById("explicitFilter").value;

    const minDanceability = parseFloat(document.getElementById("minDanceability").value) || 0;
    const maxDanceability = parseFloat(document.getElementById("maxDanceability").value) || 1;

    const minEnergy = parseFloat(document.getElementById("minEnergy").value) || 0;
    const maxEnergy = parseFloat(document.getElementById("maxEnergy").value) || 1;

    const keyFilter = parseInt(document.getElementById("keyFilter").value);

    const minLoudness = parseFloat(document.getElementById("minLoudness").value) || -60;
    const maxLoudness = parseFloat(document.getElementById("maxLoudness").value) || 0;

    const modeFilter = document.getElementById("modeFilter").value;

    const minSpeechiness = parseFloat(document.getElementById("minSpeechiness").value) || 0;
    const maxSpeechiness = parseFloat(document.getElementById("maxSpeechiness").value) || 1;

    const minAcousticness = parseFloat(document.getElementById("minAcousticness").value) || 0;
    const maxAcousticness = parseFloat(document.getElementById("maxAcousticness").value) || 1;

    const minInstrumentalness = parseFloat(document.getElementById("minInstrumentalness").value) || 0;
    const maxInstrumentalness = parseFloat(document.getElementById("maxInstrumentalness").value) || 1;

    const minLiveness = parseFloat(document.getElementById("minLiveness").value) || 0;
    const maxLiveness = parseFloat(document.getElementById("maxLiveness").value) || 1;

    const minValence = parseFloat(document.getElementById("minValence").value) || 0;
    const maxValence = parseFloat(document.getElementById("maxValence").value) || 1;

    const timeSignatureFilter = parseInt(document.getElementById("timeSignatureFilter").value);

    return songs.filter(song => {
        const matchesGenre = selectedGenre === "All" || song.genre.toLowerCase() === selectedGenre.toLowerCase();
        const matchesTempo = song.tempo >= minTempo && song.tempo <= maxTempo;
        const matchesPopularity = song.popularity >= minPopularity && song.popularity <= maxPopularity;
        const matchesDuration = song.duration_ms >= minDuration && song.duration_ms <= maxDuration;

        const matchesExplicit =
            explicitFilter === "all" ||
            (explicitFilter === "true" && song.explicit === true) ||
            (explicitFilter === "false" && song.explicit === false);

        const matchesDanceability = song.danceability >= minDanceability && song.danceability <= maxDanceability;
        const matchesEnergy = song.energy >= minEnergy && song.energy <= maxEnergy;
        const matchesKey = keyFilter === -1 || song.key === keyFilter;
        const matchesLoudness = song.loudness >= minLoudness && song.loudness <= maxLoudness;
        const matchesMode = modeFilter === "all" || song.mode.toString() === modeFilter;

        const matchesSpeechiness = song.speechiness >= minSpeechiness && song.speechiness <= maxSpeechiness;
        const matchesAcousticness = song.acousticness >= minAcousticness && song.acousticness <= maxAcousticness;
        const matchesInstrumentalness = song.instrumentalness >= minInstrumentalness && song.instrumentalness <= maxInstrumentalness;
        const matchesLiveness = song.liveness >= minLiveness && song.liveness <= maxLiveness;
        const matchesValence = song.valence >= minValence && song.valence <= maxValence;
        const matchesTimeSignature = timeSignatureFilter === -1 || song.time_signature == timeSignatureFilter;

        return (
            matchesGenre &&
            matchesTempo &&
            matchesPopularity &&
            matchesDuration &&
            matchesExplicit &&
            matchesDanceability &&
            matchesEnergy &&
            matchesKey &&
            matchesLoudness &&
            matchesMode &&
            matchesSpeechiness &&
            matchesAcousticness &&
            matchesInstrumentalness &&
            matchesLiveness &&
            matchesValence &&
            matchesTimeSignature
        );
    });
}

function loginWithSpotify() {
    const client_id = 'f7875920cd894ddf93b4bbc32bb2fb8a';
    const redirect_uri = 'https://mz02156.github.io/CariBeats-Web/';
    const scope = 'streaming user-read-email user-read-private user-library-read user-read-playback-state user-modify-playback-state';

    const auth_url = `https://accounts.spotify.com/authorize?client_id=${client_id}&response_type=token&redirect_uri=${encodeURIComponent(redirect_uri)}&scope=${encodeURIComponent(scope)}`;
    window.location.href = auth_url;
}


class DatasetLoader {
    static async loadSongs() {
        try {
            const response = await fetch('songs.json');
            const data = await response.json();

            console.log(`Loaded ${data.length} songs from local dataset.`);

            return data.map(item => new Song(
                item.id || '', 
                item.artists || '', 
                item.album || '', 
                item.title || '', 
                item.popularity || 0, 
                item.duration_ms || 0, 
                item.explicit || false, 
                item.danceability || 0, 
                item.energy || 0, 
                item.key || 0, 
                item.loudness || 0, 
                item.mode || 0, 
                item.speechiness || 0, 
                item.acousticness || 0, 
                item.instrumentalness || 0, 
                item.liveness || 0, 
                item.valence || 0, 
                item.tempo || 0, 
                item.genre || ''
            ));
        } catch (error) {
            console.error("Failed to load local dataset: ", error);
            return [];
        }
    }
}

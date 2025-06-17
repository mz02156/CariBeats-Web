package edu.georgiasouthern.csci5530;

/**
 * BPMClusterHelper provides utility methods to categorize songs into
 * mood labels or cluster IDs based on their tempo (BPM - Beats Per Minute).
 */
public class BPMClusterHelper {

    /**
     * Returns a mood label based on the tempo value.
     *
     * @param tempo the beats per minute of the song
     * @return "Chill" for tempo < 90,
     *         "Moderate" for tempo between 90 and 120 (inclusive),
     *         "Hype" for tempo > 120
     */
    public static String getMoodLabel(float tempo) {
        if (tempo < 90)
            return "Chill";      // Slow tempo, relaxed feel
        else if (tempo <= 120)
            return "Moderate";   // Medium tempo, balanced energy
        else
            return "Hype";       // Fast tempo, energetic
    }

    /**
     * Returns a numeric cluster ID corresponding to the tempo group.
     * Useful for ML clustering or internal categorization logic.
     *
     * @param tempo the beats per minute of the song
     * @return 0 for "Chill" (< 90 BPM),
     *         1 for "Moderate" (90–120 BPM),
     *         2 for "Hype" (> 120 BPM)
     */
    public static int getClusterId(float tempo) {
        if (tempo < 90)
            return 0;    // Cluster 0: Chill
        else if (tempo <= 120)
            return 1;    // Cluster 1: Moderate
        else
            return 2;    // Cluster 2: Hype
    }
}

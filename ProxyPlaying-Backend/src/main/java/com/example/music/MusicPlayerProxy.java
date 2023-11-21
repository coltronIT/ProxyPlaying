package com.example.music;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Objects;

public class MusicPlayerProxy {
    private MusicPlayer musicPlayer;
    public MusicPlayerProxy() {
        this.musicPlayer = null;
    };

    public String playSong(String songId) {
        if (this.musicPlayer != null) {
            return this.musicPlayer.playSong(songId);
        } else {
            RestTemplate restTemplate = new RestTemplate();

            String flaskAppUrl = "http://localhost:5000"; // URL of your Flask app
            String endpoint = "/api/all_songs"; // Endpoint in your Flask app that handles the song playing

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(flaskAppUrl + endpoint);
            JSONObject obj = new JSONObject(jsonString);
            parsedSongs = null;

            try {
                parsedSongs = Objects.requireNonNull(
                        restTemplate.exchange(
                                builder.toUriString(),
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<Map<String, String>>() {

                                }).getBody());
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: Could not play the song.";
            }
            this.setMusicPlayer(new MusicPlayer(parsedSongs));
            return parsedSongs.get(songId);
        }
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }
}

package com.example.music;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MusicPlayer implements MusicService {

    private Map<String, String> songs = new HashMap<>();

    public MusicPlayer(){
        RestTemplate restTemplate = new RestTemplate();

        String flaskAppUrl = "http://localhost:5000"; // URL of your Flask app
        String endpoint = "/api/all_songs"; // Endpoint in your Flask app that handles the song playing

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(flaskAppUrl + endpoint);
        Map<String, String> parsedSongs = null;

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
        }
        this.songs = parsedSongs;
    };

    public MusicPlayer(Map<String, String> songs) {
        this.songs = songs;
    }

    public String playSong(String songId) {
        return this.songs.get(songId);
    }
}

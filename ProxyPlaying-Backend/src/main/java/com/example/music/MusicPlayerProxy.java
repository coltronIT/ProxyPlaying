package com.example.music;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
            ObjectMapper mapper = new ObjectMapper();


            String flaskAppUrl = "http://localhost:5000"; // URL of your Flask app
            String endpoint = "/api/all_songs"; // Endpoint in your Flask app that handles the song playing

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(flaskAppUrl + endpoint);

            String parsedSongs = null;

            try {
                parsedSongs = Objects.requireNonNull(
                        restTemplate.exchange(
                                builder.toUriString(),
                                HttpMethod.GET,
                                null,
                                String.class
                                ).getBody());
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: Could not play the song.";
            }
            List<Song> songs = null;

            try {
                songs = mapper.readValue(parsedSongs, new TypeReference<List<Song>>(){});
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            Map<String, String> songData = songs.stream().collect(Collectors.toMap(Song::getId, Song::getSongDisplay));

            this.setMusicPlayer(new MusicPlayer(songData));
            return songData.get(songId);
        }
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }
}

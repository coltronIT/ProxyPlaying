package com.example.music.service;

import org.springframework.stereotype.Service;

import com.example.music.MusicPlayer;
import com.example.music.MusicPlayerProxy;

@Service
public class MusicPlayingService {
    private static final MusicPlayerProxy musicPlayerProxy = new MusicPlayerProxy();

    public String playSongWithProxy(String songId) {
        return musicPlayerProxy.playSong(songId);
    }
    public String playSongWithoutProxy(String songId) {
        MusicPlayer musicPlayer = new MusicPlayer();
        return musicPlayer.playSong(songId);
    }

}

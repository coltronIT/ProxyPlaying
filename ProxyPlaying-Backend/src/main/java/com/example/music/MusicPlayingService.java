package com.example.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

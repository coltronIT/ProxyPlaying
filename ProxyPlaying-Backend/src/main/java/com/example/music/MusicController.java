package com.example.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MusicController {

    @Autowired
    MusicPlayingService musicPlayingService;
//    private final MusicService expensiveMusicService;
//    private final MusicService cachedMusicService;
//
//    @Autowired
//    public MusicController(MusicPlayer expensiveMusicService, CachedMusicService cachedMusicService) {
//        this.expensiveMusicService = expensiveMusicService;
//        this.cachedMusicService = cachedMusicService;
//    }

    @GetMapping("play/proxy")
    public ResponseEntity<String> playCachedSong(@RequestParam String songId) {
        String result = musicPlayingService.playSongWithProxy(songId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("play")
    public ResponseEntity<String> playExpensiveSong(@RequestParam String songId) {
        String result = musicPlayingService.playSongWithoutProxy(songId);
        return ResponseEntity.ok(result);
    }
}

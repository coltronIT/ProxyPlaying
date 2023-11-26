package com.example.music.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.music.service.MusicPlayingService;

@RestController
@RequestMapping("/api")
public class MusicController {

    @Autowired
    MusicPlayingService musicPlayingService;

    @GetMapping("play/proxy")
    public ResponseEntity<Map<String, String>> playCachedSong(@RequestParam String songId) {
        Map<String, String> map = new HashMap<>();
        String result = musicPlayingService.playSongWithProxy(songId);
        map.put("songDisplay", result);
        return ResponseEntity.ok(map);
    }

    @GetMapping("play")
    public ResponseEntity<Map<String, String>> playExpensiveSong(@RequestParam String songId) {
        Map<String, String> map = new HashMap<>();
        String result = musicPlayingService.playSongWithoutProxy(songId);
        map.put("songDisplay", result);
        return ResponseEntity.ok(map);
    }

}

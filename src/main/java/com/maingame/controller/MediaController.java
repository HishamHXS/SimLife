package com.maingame.controller;

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MediaController {

    private static final Logger LOGGER = Logger.getLogger(MediaController.class.getName());
    private static final Map<String, AudioClip> soundCache = new HashMap<>();
    private static final List<String> trackPaths = new ArrayList<>();

    private static int currentTrackIndex = 0;
    private static MediaPlayer mediaPlayer;

    public static void playSound(String soundFile) {
        if (!soundCache.containsKey(soundFile)) {
            URL resource = MediaController.class.getResource("/sounds/" + soundFile);

            if (resource != null) {
                soundCache.put(soundFile, new AudioClip(resource.toString()));
            } else {
                LOGGER.log(Level.FINE, "Sound file not found: " + soundFile);
                return;
            }
        }
        soundCache.get(soundFile).play();
    }

    private static void loadTracks() {
        URL trackDirURL = MediaController.class.getResource("/sounds/tracks/");
        if (trackDirURL == null) {
            System.out.println("Track directory not found!");
            return;
        }

        File trackDir = new File(trackDirURL.getFile());
        File[] files = trackDir.listFiles((_, name) -> name.endsWith(".mp3"));

        if (files != null) {
            for (File file : files) {
                trackPaths.add(file.toURI().toString());
            }
        }
    }

    private static void playTrack(int index) {
        if (index >= trackPaths.size()) {
            index = 0;
        }

        Media media = new Media(trackPaths.get(index));
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnEndOfMedia(() -> {
            currentTrackIndex = (currentTrackIndex + 1) % trackPaths.size();
            playTrack(currentTrackIndex);
        });

        mediaPlayer.play();
    }

    public static void playButtonClick() {
        playSound("button_click_sound.wav");
    }

    public static void playBackgroundMusic() {
        if (trackPaths.isEmpty()) {
            loadTracks();
        }

        if (!trackPaths.isEmpty()) {
            playTrack(currentTrackIndex);
        } else {
            LOGGER.log(Level.FINE, "No tracks found in resources/sounds/tracks/");
        }
    }

    public static void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void skipTrack() {
        if (!trackPaths.isEmpty()) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                currentTrackIndex = (currentTrackIndex + 1) % trackPaths.size();
                playTrack(currentTrackIndex);
            }
        }
    }
}

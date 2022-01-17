package com.hustunique.musica.player;

import android.media.MediaPlayer;

import com.hustunique.musica.MyApplication;

public class Track {
    private MediaPlayer player;
    private int id;

    public int getId() {
        return id;
    }

    public Track(int resId) {
        init(resId);
    }

    public void play() {
        if (!player.isPlaying())
            player.start();
    }

    public void pause() {
        if (player.isPlaying())
            player.pause();
    }

    public void release() {
        player.release();
    }

    public void reset() {
        player.reset();
    }

    public void init(int resId) {
        id = resId;
        player = MediaPlayer.create(MyApplication.context, resId);
        player.setLooping(true);
        play();
    }

}
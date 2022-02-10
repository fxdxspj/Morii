package com.hustunique.morii.music

import com.hustunique.morii.util.AudioExoPlayerUtil

class MusicSelectPresenter internal constructor(private val view: MusicSelectContract.IView) :
    MusicSelectContract.IPresenter {
    override fun switchMusic(position: Int) {
        AudioExoPlayerUtil.playMusic(position)
    }

    override fun stopMusic() {
        AudioExoPlayerUtil.pauseMusicPlayer()
    }
}
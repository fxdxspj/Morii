package com.hustunique.morii.util

import android.net.Uri
import android.provider.MediaStore
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.hustunique.morii.util.MyApplication.Companion.musicTabList
import com.hustunique.morii.util.MyApplication.Companion.soundItemList

object AudioExoPlayerUtil {
    val volumes = floatArrayOf(0.3f, 0.15f, 0.08f)
    private val musicPlayer = ExoPlayer.Builder(MyApplication.context).build()
    private const val TAG = "AudioExoPlayerUtil"
    private val soundPlayerList: MutableList<ExoPlayer> = ArrayList()
    private var listener: OnReadyListener? = null
    private var duration: Long = 0
    private val mediaItemMap: MutableMap<Int, MediaItem> = HashMap()

    fun initMusicPlayer() {
        for (musicTab in musicTabList) {
            val mediaItem = MediaItem.fromUri(UriParser(musicTab.musicResId))
            musicPlayer.addMediaItem(mediaItem)
        }
        musicPlayer.volume = 0.6f
        musicPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ONE
        musicPlayer.prepare()
        musicPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                if (playbackState == ExoPlayer.STATE_READY) {
                    duration = musicPlayer.duration
                    if (listener != null && duration > 0) {
                        listener!!.onReady(musicPlayer.duration)
                    }
                }
            }
        })
    }

    fun initSoundPlayer() {
        for (i in 0..8) {
            val player = ExoPlayer.Builder(MyApplication.Companion.context).build()
            player.volume = volumes[i / 3]
            player.repeatMode = ExoPlayer.REPEAT_MODE_ONE
            soundPlayerList.add(player)
        }
    }

    fun playMusic(num: Int) {
        musicPlayer.seekToDefaultPosition(num)
        musicPlayer.play()
    }

    fun setSoundPlayer(soundItemId: Int, position: Int) {
        val player = soundPlayerList[position]
        val soundResId: Int =
            soundItemList[soundItemId].soundResIds[position % 3]
        player.setMediaItem(mediaItemMap[soundResId]!!)
        player.prepare()
    }

    fun stopPlayingSoundItem(position: Int) {
        val player = soundPlayerList[position]
        player.pause()
        player.removeMediaItem(0)
    }

    fun stopAllSoundPlayers() {
        for (player in soundPlayerList) {
            run { if (player.isPlaying) player.pause() }
        }
    }

    fun startSoundPlayer(position: Int) {
        soundPlayerList[position].play()
    }

    fun resetAllSoundPlayers() {
        for (player in soundPlayerList) {
            player.removeMediaItem(0)
        }
    }

    fun startAllSoundPlayers() {
        for (player in soundPlayerList) {
            if (player.playbackState == ExoPlayer.STATE_READY) player.play()
        }
    }

    fun pauseMusicPlayer() {
        musicPlayer.pause()
    }

    fun UriParser(resId: Int): Uri {
        return Uri.parse(
            "android.resource://"
                    + MyApplication.context.packageName + "/" + resId
        )
    }

    fun pauseAllPlayers() {
        stopAllSoundPlayers()
        pauseMusicPlayer()
    }

    fun startAllPlayers() {
        startAllSoundPlayers()
        musicPlayer.play()
    }

    val currentPosition: Long
        get() = musicPlayer.currentPosition
    val isPlaying: Boolean
        get() = musicPlayer.isPlaying

    fun getDuration(): Long {
        return musicPlayer.duration
    }

    fun setListener(listener: OnReadyListener?) {
        AudioExoPlayerUtil.listener = listener
    }

    fun initSoundItems() {
        for (item in soundItemList) {
            for (resId in item.soundResIds)
                mediaItemMap[resId] = MediaItem.fromUri(UriParser(resId))
        }
    }
}
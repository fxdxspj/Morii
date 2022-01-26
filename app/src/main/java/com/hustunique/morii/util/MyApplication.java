package com.hustunique.morii.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.hustunique.morii.design.SoundItem;
import com.hustunique.morii.home.MusicDiaryItem;
import com.hustunique.morii.music.MusicTab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import morii.R;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public final static List<MusicTab> musicTabList = new ArrayList<>();
    public final static List<SoundItem> soundItemList = new ArrayList<>();
    public final static List<MusicDiaryItem> musicDiaryList = new ArrayList<>();
    public static AudioExoPlayerUtil playerUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        playerUtil = new AudioExoPlayerUtil();
        initResourcesList();
    }

    private void initResourcesList() {
        for (int i = 1; i <= 10; i++) {
            MusicDiaryItem musicDiaryItem = new MusicDiaryItem();
            musicDiaryItem.setItemID(i);
            musicDiaryItem.setDate(Calendar.getInstance().getTime().toString());
            musicDiaryItem.setTitle("Default Title " + i);
            musicDiaryItem.setArticle("This is content of the article " + i);
            musicDiaryList.add(musicDiaryItem);
        }
        soundItemList.add(new SoundItem(R.drawable.outline_air_24));
        soundItemList.add(new SoundItem(R.drawable.outline_flutter_dash_24));
        soundItemList.add(new SoundItem(R.drawable.outline_free_breakfast_24));
        soundItemList.add(new SoundItem(R.drawable.outline_grass_24));
        soundItemList.add(new SoundItem(R.drawable.outline_air_24));
        soundItemList.add(new SoundItem(R.drawable.outline_flutter_dash_24));
        soundItemList.add(new SoundItem(R.drawable.outline_free_breakfast_24));
        soundItemList.add(new SoundItem(R.drawable.outline_grass_24));
        soundItemList.add(new SoundItem(R.drawable.outline_air_24));
        soundItemList.add(new SoundItem(R.drawable.outline_flutter_dash_24));
        soundItemList.add(new SoundItem(R.drawable.outline_free_breakfast_24));
        soundItemList.add(new SoundItem(R.drawable.outline_grass_24));

        musicTabList.add(new MusicTab("宁静", R.drawable.x7, R.raw.peaceful));
        musicTabList.add(new MusicTab("律动", R.drawable.x1, R.raw.jazz));
        musicTabList.add(new MusicTab("宁静", R.drawable.x7, R.raw.peaceful));
        musicTabList.add(new MusicTab("律动", R.drawable.x1, R.raw.jazz));
        musicTabList.add(new MusicTab("宁静", R.drawable.x7, R.raw.peaceful));
        musicTabList.add(new MusicTab("律动", R.drawable.x1, R.raw.jazz));

        playerUtil.initMusicPlayer();
    }
    public static SoundItem getSoundItemThroughIconID(int iconID){
        switch (iconID){
            case R.drawable.x1:
                return soundItemList.get(0);
            case R.drawable.x2:
                return soundItemList.get(1);
            case R.drawable.x3:
                return soundItemList.get(2);
            case R.drawable.x4:
                return soundItemList.get(3);
            case R.drawable.x5:
                return soundItemList.get(4);
            case R.drawable.x6:
                return soundItemList.get(5);
            case R.drawable.x7:
                return soundItemList.get(6);
            default:
                return soundItemList.get(8);
        }
    }
    public static void clearAllResIdInSoundItemList(){
        for (SoundItem soundItem:soundItemList){
            soundItem.getResIdList().clear();
        }
    }
}
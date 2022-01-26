package com.hustunique.morii.home;

import static com.hustunique.morii.util.MyApplication.musicDiaryList;

public class PresenterMain implements HomePageContract.IPresenter {
    HomePageContract.IView view;
    HomePageContract.IModel model;

    PresenterMain(HomePageContract.IView iViewMain){
        this.view = iViewMain;
    }

    @Override
    public void getUI(){
        view.GetRecyclerView(musicDiaryList);
    }

    @Override
    public void setRecyclerViewItem(int position) {

    }


}
package com.example.ji066375.mvptest.Model;

import com.example.ji066375.mvptest.Common.MVP;

/**
 * Created by ji066375 on 30.3.2016.
 */
public class MainModel implements MVP.ModelOPs{

    private MVP.RequiredPresenterOps mPresenter;

    /**
     * Sent from {@link com.example.ji066375.mvptest.Presenter.MainPresenter#onDestroy(boolean)}
     * Should stop/kill operations that could be running
     * and aren't needed anymore
     */
    public MainModel(MVP.RequiredPresenterOps presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        // destroy actions
    }

    @Override
    public void insertNote(Note note) {
        // business logic
        mPresenter.onNoteInserted(note);
    }

    @Override
    public void removeNote(Note note) {
        // business logic
        mPresenter.onNoteRemoved(note);
    }
}

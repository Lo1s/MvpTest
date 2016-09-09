package com.example.ji066375.mvptest.Presenter;

import com.example.ji066375.mvptest.Common.MVP;
import com.example.ji066375.mvptest.Model.MainModel;
import com.example.ji066375.mvptest.Model.Note;

import java.lang.ref.WeakReference;

/**
 * Created by ji066375 on 30.3.2016.
 */
public class MainPresenter implements MVP.PresenterOps, MVP.RequiredPresenterOps {

    // View reference
    private WeakReference<MVP.RequiredViewOps> mView;
    // Model reference
    private MVP.ModelOPs mModel;

    // Configuration change state
    private boolean mIsChangingConfig;

    public MainPresenter(MVP.RequiredViewOps view) {
        this.mView = new WeakReference<MVP.RequiredViewOps>(view);
        this.mModel = new MainModel(this);
    }

    /**
     * Sent from Activity after a configuration changes
     * @param view  View reference
     */
    @Override
    public void onConfigurationChanged(MVP.RequiredViewOps view) {
        mView = new WeakReference<MVP.RequiredViewOps>(view);
    }

    /**
     * Receives {@link com.example.ji066375.mvptest.View.MainActivity#onDestroy()} event
     * @param isChangingConfig  Config change state
     */
    @Override
    public void onDestroy(boolean isChangingConfig) {
        mView = null;
        mIsChangingConfig = isChangingConfig;
        if (!isChangingConfig)
            mModel.onDestroy();
    }

    /**
     * Called by user interaction from {@link com.example.ji066375.mvptest.View.MainActivity}
     * creates a new Note
     */
    @Override
    public void newNote(String textNote) {
        Note note = new Note(textNote);
        mModel.insertNote(note);
    }

    /**
     * Called from {@link com.example.ji066375.mvptest.View.MainActivity},
     * Removes a Note
     */
    @Override
    public void removeNote(Note note) {
        mModel.removeNote(note);
    }

    /**
     * Called from {@link MainModel}
     * when a Note is inserted successfully
     */
    @Override
    public void onNoteInserted(Note newNote) {
        mView.get().showToast("New Note: " + newNote.getNote());
    }

    /**
     * Receives call from {@link MainModel}
     * when Note is removed
     */
    @Override
    public void onNoteRemoved(Note removedNote) {
        mView.get().showToast("Note removed");
    }

    /**
     * receive errors
     */
    @Override
    public void onError(String errorMsg) {
        mView.get().showAlert(errorMsg);
    }
}

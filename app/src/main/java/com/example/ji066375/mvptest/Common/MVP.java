package com.example.ji066375.mvptest.Common;

import com.example.ji066375.mvptest.Model.Note;


/*
 * Aggregates all communication operations between MVP pattern layer:
 * Model, View and Presenter
 */

public interface MVP {


    /**
     * View mandatory methods. Available to Presenter
     *      Presenter -> View
     */
    interface RequiredViewOps {
        void showToast(String msg);
        void showAlert(String msg);
    }

    /**
     * Operations offered from Presenter to View
     *      View -> Presenter
     */
    interface PresenterOps {
        void onConfigurationChanged(RequiredViewOps view);
        void onDestroy(boolean isChangingConfig);
        void newNote(String textNote);
        void removeNote(Note note);
    }

    /**
     * Operations offered from Presenter to Model
     *      Model -> Presenter
     */
    interface RequiredPresenterOps {
        void onNoteInserted(Note newNote);
        void onNoteRemoved(Note removedNote);
        void onError(String errorMsg);
    }

    /**
     * Model operations offered to Presenter
     *      Presenter -> Model
     */
    interface ModelOPs {
        void insertNote(Note note);
        void removeNote(Note note);
        void onDestroy();
    }
}

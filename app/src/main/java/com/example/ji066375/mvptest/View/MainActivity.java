package com.example.ji066375.mvptest.View;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ji066375.mvptest.Common.MVP;
import com.example.ji066375.mvptest.Common.StateMaintainer;
import com.example.ji066375.mvptest.Presenter.MainPresenter;
import com.example.ji066375.mvptest.R;

public class MainActivity extends AppCompatActivity implements MVP.RequiredViewOps {

    protected final String TAG = getClass().getSimpleName();

    // Responsible to maintain the Objects state
    // during changing configuration
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer(getFragmentManager(), TAG);

    // Presenter operations
    private MVP.PresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Initialize and restart the Presenter.
     * This method should be called after {@link android.app.Activity#onCreate(Bundle)}
     */
    private void startMVPOps() {
        try {
            if (mStateMaintainer.firstTimeIn()) {
                Log.d(TAG, "onCreate() called for the first time");
                initialize(this);
            } else {
                Log.d(TAG, "onCreate() called more than once");
                reinitialize(this);
            }
        } catch (Fragment.InstantiationException | IllegalAccessException ex) {
            Log.d(TAG, "onCreate() " + ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Initialize relevant MVP Objects.
     * Creates a Presenter instance, saves the presenter in {@link StateMaintainer}
     */
    private void initialize(MVP.RequiredViewOps view) {
        mPresenter = new MainPresenter(view);
        mStateMaintainer.put(MVP.PresenterOps.class.getSimpleName(), mPresenter);
    }

    /**
     * Recovers Presenter and informs Presenter that occurred a config change.
     * If Presenter has been lost, recreates a instance
     */
    private void reinitialize(MVP.RequiredViewOps view) throws Fragment.InstantiationException,
        IllegalAccessException {
        mPresenter = mStateMaintainer.get(MVP.PresenterOps.class.getSimpleName());

        if (mPresenter == null) {
            Log.w(TAG, "Recreating Presenter");
            initialize(view);
        } else {
            mPresenter.onConfigurationChanged(view);
        }
    }

    // Show alert dialog
    @Override
    public void showAlert(String msg) {
        // show alert box
    }

    // Show toast

    @Override
    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}

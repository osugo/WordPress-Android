package org.wordpress.android.ui.accounts;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Window;

import org.wordpress.android.R;

public class NewBlogActivity extends SherlockFragmentActivity {
    public static final String KEY_START_MODE = "start-mode";
    public static final int CREATE_BLOG = 1;
    public static final int CREATE_BLOG_LOGOUT_ON_CANCEL = 2;

    private NewBlogFragment mNewBlogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_blog);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mNewBlogFragment = (NewBlogFragment) fragmentManager.
                findFragmentById(R.id.new_blog_fragment);
        if (getActionMode() == CREATE_BLOG_LOGOUT_ON_CANCEL) {
            mNewBlogFragment.setSignoutOnCancelMode(true);
        }
    }

    private int getActionMode() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getInt(KEY_START_MODE, CREATE_BLOG);
        }
        return CREATE_BLOG;
    }

    @Override
    public void onBackPressed() {
        if (mNewBlogFragment.isSignoutOnCancelMode()) {
            mNewBlogFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
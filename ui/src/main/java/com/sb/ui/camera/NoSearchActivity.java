
package com.sb.ui.camera;

import android.support.v7.app.AppCompatActivity;

public class NoSearchActivity extends AppCompatActivity {
    @Override
    public boolean onSearchRequested() {
        return false;
    }
}

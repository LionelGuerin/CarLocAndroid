package fr.imie.am.carloc.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import fr.imie.am.carloc.R;

public class HomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_home);
    }
}

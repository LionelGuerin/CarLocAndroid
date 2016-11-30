package fr.imie.am.carloc.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import fr.imie.am.carloc.R;

public class UserShowActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_show);
    }
}

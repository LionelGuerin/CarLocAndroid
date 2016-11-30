package fr.imie.am.carloc.view;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.imie.am.carloc.R;
import fr.imie.am.carloc.data.CarLocSQLiteOpenHelper;
import fr.imie.am.carloc.data.UserSQLiteAdapter;
import fr.imie.am.carloc.entity.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private EditText etFirstname;
    private EditText etLastname;
    private EditText etUsername;
    private EditText etPassword;
    private Button btSave;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.etFirstname = (EditText) view.findViewById(R.id.etFirstname);
        this.etLastname = (EditText) view.findViewById(R.id.etLastname);
        this.etUsername = (EditText) view.findViewById(R.id.etUsername);
        this.etPassword = (EditText) view.findViewById(R.id.etPassword);
        this.btSave = (Button) view.findViewById(R.id.btSave);

        User user = this.getUserInPreferences();

        this.etFirstname.setText(user.getFirstname());
        this.etLastname.setText(user.getLastname());
        this.etUsername.setText(user.getUsername());

        this.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setFirstname(HomeFragment.this.etFirstname.getText().toString());
                user.setLastname(HomeFragment.this.etLastname.getText().toString());
                user.setUsername(HomeFragment.this.etUsername.getText().toString());
                user.setPassword(HomeFragment.this.etPassword.getText().toString());

                HomeFragment.this.saveUserInPreferences(user);
                HomeFragment.this.insertUserInDatabase(user);
            }
        });

        return view;
    }

    private void saveUserInPreferences(User user) {


        SharedPreferences settings =
                this.getActivity().getSharedPreferences("carloc", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        //don't store any password in preferences - use a token
        editor.putString("firstname", user.getFirstname());
        editor.putString("lastname", user.getLastname());
        editor.putString("username", user.getUsername());

        editor.commit();
    }

    private User getUserInPreferences() {
        User user = new User();

        SharedPreferences settings =
                this.getActivity().getSharedPreferences("carloc", Context.MODE_PRIVATE);

        user.setFirstname(settings.getString("firstname", ""));
        user.setLastname(settings.getString("lastname", ""));
        user.setUsername(settings.getString("username", ""));

        return user;
    }

    private void insertUserInDatabase(User user) {
        UserSQLiteAdapter adapter = new UserSQLiteAdapter(this.getActivity());

        if (adapter.insert(user)) {
            Toast.makeText(this.getActivity(), "id : " + user.getId(),
                    Toast.LENGTH_LONG).show();
        }
    }
}

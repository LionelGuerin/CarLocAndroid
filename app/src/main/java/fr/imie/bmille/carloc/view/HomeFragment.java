package fr.imie.bmille.carloc.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.imie.bmille.carloc.R;
import fr.imie.bmille.carloc.data.UserSQLiteAdapter;
import fr.imie.bmille.carloc.entity.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etUserName;
    private EditText etPassword;
    private Button btSave;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.etFirstName = (EditText)view.findViewById(R.id.firstName);
        this.etLastName = (EditText)view.findViewById(R.id.lastName);
        this.etUserName = (EditText)view.findViewById(R.id.username);
        this.etPassword = (EditText)view.findViewById(R.id.password);
        this.btSave = (Button)view.findViewById(R.id.btSave);

        User user = this.getUserInPreferences();

        this.etFirstName.setText(user.getFirstName());
        this.etLastName.setText(user.getLastName());
        this.etUserName.setText(user.getUsername());

        this.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setFirstName(HomeFragment.this.etFirstName.getText().toString());
                user.setLastName(HomeFragment.this.etLastName.getText().toString());
                user.setUsername(HomeFragment.this.etUserName.getText().toString());

                HomeFragment.this.saveUserInPreference(user);
            }
        });

        return view;
    }

    private void saveUserInPreference(User user)
    {
        SharedPreferences settings = this.getActivity().getSharedPreferences("carloc", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString("firstname", user.getFirstName());
        editor.putString("lastname", user.getLastName());
        editor.putString("username", user.getUsername());

        editor.commit();
    }

    private User getUserInPreferences()
    {
        User user = new User();

        SharedPreferences settings = this.getActivity().getSharedPreferences("carloc", Context.MODE_PRIVATE);

        user.setFirstName(settings.getString("firstname", ""));
        user.setLastName(settings.getString("lastname", ""));
        user.setUsername(settings.getString("username", ""));

        return user;
    }

    private void insertUserInDatabase(User user) {
        UserSQLiteAdapter adapter = new UserSQLiteAdapter(this.getActivity());

        adapter.insert(user);

        Toast.makeText(this.getActivity(), "id : " + user.getId(), Toast.LENGTH_LONG).show();
    }
}

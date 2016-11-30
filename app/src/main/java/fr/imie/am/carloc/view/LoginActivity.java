package fr.imie.am.carloc.view;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.imie.am.carloc.R;
import fr.imie.am.carloc.data.UserSQLiteAdapter;
import fr.imie.am.carloc.data.VehiculeSQLiteAdapter;
import fr.imie.am.carloc.entity.User;
import fr.imie.am.carloc.entity.Vehicule;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Data for test
        User user = new User();
        user.setLastname("toto");
        user.setFirstname("tata");
        user.setUsername("toto_tata");
        user.setPassword("123456");

        Vehicule vehicule = new Vehicule();
        vehicule.setModel("3008");
        vehicule.setBrand("Peugeot");
        vehicule.setUser(user);

        UserSQLiteAdapter userAdapter = new UserSQLiteAdapter(this);
        userAdapter.insert(user);

        VehiculeSQLiteAdapter vehiculeAdapter = new VehiculeSQLiteAdapter(this);
        vehiculeAdapter.insert(vehicule);


    }
}

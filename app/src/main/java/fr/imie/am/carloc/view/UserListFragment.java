package fr.imie.am.carloc.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import fr.imie.am.carloc.R;
import fr.imie.am.carloc.data.UserSQLiteAdapter;
import fr.imie.am.carloc.entity.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class UserListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView list;
    private List<User> users;

    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        this.list = (ListView) view.findViewById(R.id.list);

        UserSQLiteAdapter sqLiteAdapter = new UserSQLiteAdapter(this.getActivity());
        this.users = sqLiteAdapter.getAll();

        UserListAdapter adapter = new UserListAdapter(this.getActivity(), users);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        User user = (User) this.list.getItemAtPosition(position);

        Bundle b = new Bundle();
        b.putSerializable(User.KEY, user);

        Intent intent = new Intent(this.getActivity(), UserShowActivity.class);
        intent.putExtras(b);

        this.startActivity(intent);
    }
}

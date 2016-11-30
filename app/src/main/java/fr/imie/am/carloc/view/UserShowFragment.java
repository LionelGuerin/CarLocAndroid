package fr.imie.am.carloc.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.imie.am.carloc.R;
import fr.imie.am.carloc.entity.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserShowFragment extends Fragment {
    private User user;
    private TextView tvLastname;
    private TextView tvFirstname;


    public UserShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.user = (User) this.getActivity().getIntent().getExtras().getSerializable(User.KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_show, container, false);

        this.tvFirstname = (TextView) view.findViewById(R.id.tvFirstname);
        this.tvLastname = (TextView) view.findViewById(R.id.tvLastname);

        this.tvFirstname.setText(this.user.getFirstname());
        this.tvLastname.setText(this.user.getLastname());

        return view;
    }

}

package fr.imie.am.carloc.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import fr.imie.am.carloc.R;
import fr.imie.am.carloc.data.UserSQLiteAdapter;
import fr.imie.am.carloc.data.VehiculeSQLiteAdapter;
import fr.imie.am.carloc.entity.User;
import fr.imie.am.carloc.entity.Vehicule;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehiculeListFragment extends Fragment {
    private User user;
    private ListView list;
    private List<Vehicule> vehicules;

    public VehiculeListFragment() {
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


        View view = inflater.inflate(R.layout.fragment_vehicule_list, container, false);

        this.list = (ListView) view.findViewById(R.id.list);

        VehiculeSQLiteAdapter sqLiteAdapter = new VehiculeSQLiteAdapter(this.getActivity());
        this.vehicules = sqLiteAdapter.getByUserId(this.user.getId());

        VehiculeListAdapter adapter = new VehiculeListAdapter(this.getActivity(), vehicules);

        list.setAdapter(adapter);

        return view;
    }

}

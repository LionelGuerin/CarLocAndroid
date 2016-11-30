package fr.imie.am.carloc.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.imie.am.carloc.R;
import fr.imie.am.carloc.entity.User;

/**
 * Created by tact on 28/11/16.
 */

public class UserListAdapter extends ArrayAdapter<User> {

    public UserListAdapter(Context context, List<User> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_user, parent, false);
        }
        // Lookup view for data population
        TextView tvFirstname = (TextView) convertView.findViewById(R.id.tvFirstname);
        TextView tvLastname = (TextView) convertView.findViewById(R.id.tvLastname);
        // Populate the data into the template view using the data object
        tvFirstname.setText(user.getFirstname());
        tvLastname.setText(user.getLastname());
        // Return the completed view to render on screen
        return convertView;
    }
}

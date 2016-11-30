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
import fr.imie.am.carloc.entity.Vehicule;

/**
 * Created by tact on 28/11/16.
 */

public class VehiculeListAdapter extends ArrayAdapter<Vehicule> {

    public VehiculeListAdapter(Context context, List<Vehicule> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Vehicule vehicule = this.getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_vehicule, parent, false);
        }
        // Lookup view for data population
        TextView tvBrand = (TextView) convertView.findViewById(R.id.tvBrand);
        TextView tvModel = (TextView) convertView.findViewById(R.id.tvModel);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        // Populate the data into the template view using the data object
        tvBrand.setText(vehicule.getBrand());
        tvModel.setText(vehicule.getModel());
        tvDate.setText(vehicule.getDateCreated().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}

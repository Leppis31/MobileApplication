package fi.lab.natiivi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Random;

public class MapsFragment extends Fragment {
    GoogleMap mMap;
    String LatlngLocation;
    String LocationName;

    ArrayList<String> Locations = new ArrayList<String>();
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng lahti = new LatLng(60.98267, 25.66151);
            Marker mr = googleMap.addMarker(new MarkerOptions().position(lahti).title("Lahti"));
            mr.setDraggable(true);
            // default/original/first LatlngLocation is Lahti
            LatlngLocation = lahti.toString();
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(lahti));
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);
            // Add marker
            mMap.setOnMapClickListener(latLng -> {
                Marker m = mMap.addMarker(new MarkerOptions().position(latLng).title("M"+new Random().nextInt()));
                // update LatlngLocation
                LatlngLocation = m.getPosition().toString();
                m.setDraggable(true);
            });
            // Remove marker
            mMap.setOnMarkerClickListener(m -> {
                m.remove();
                Toast.makeText(getContext(), "Marker: "+m.getTitle()+" "+m.getId()+" removed", Toast.LENGTH_SHORT).show();
                return true;
            });
            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDrag(@NonNull Marker marker) {
                }
                @Override
                public void onMarkerDragEnd(@NonNull Marker marker) {
                    Toast.makeText(getContext(), "MarkerEnd: "+marker.getPosition(), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onMarkerDragStart(@NonNull Marker marker) {

                }
            });
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        getParentFragmentManager().setFragmentResultListener("dataForm1", this, new FragmentResultListener() {
            @Override
            // Gets data from Fragment2 editTextPersonName
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("df1");
                LocationName = data;
                // test in console
                System.out.println(LocationName);
                System.out.println(LatlngLocation);
                // Add to list
                Locations.add(LocationName);
                Locations.add(LatlngLocation);
                // test list in console
                System.out.println(Locations);
            }
        });

        return  rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
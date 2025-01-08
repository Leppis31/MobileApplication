package fi.lab.natiivi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
public class MainActivity extends AppCompatActivity{
    private EditText editTextName;

    // Silloinkun tein fragmenteja tein aluksi väärän fragmentin, poistin sen mutten muistanut poistaa xml fileä -> joten tämän takia fragment_3.xml ja Fragment2.java nimet ovat eri.
    // Nimeä ei vissiin enään pysty vaihtaa, pitäisi tehdä kokonaan alusta.
    // Tein ohjelman silleen, että ohjelma tallentaa EditTextin omaan temporary variable ja kartasta valitun paika omaan temporary variable... Tämän jälkeen click osiossa
    // ohjelma lisää ne arraylistaan extrana, koska en keksinyt muuta tapaa esittää arvoja järkevästi ja selvästi...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapsFragment fragmentMaps = new MapsFragment();
        Fragment2 fragment2 = Fragment2.newInstance("Eka","Toka");
        // Fragments
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment2,fragment2)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment1,fragmentMaps)
                .commit();




    }
}
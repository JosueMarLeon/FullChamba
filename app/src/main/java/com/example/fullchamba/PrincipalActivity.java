package com.example.fullchamba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fullchamba.entidad.Empleado;
import com.example.fullchamba.entidad.Tarea;
import com.example.fullchamba.fragment.ActualizaEmpleadoFragment;
import com.example.fullchamba.fragment.ActualizaTareaFragment;
import com.example.fullchamba.fragment.DashboardFragment;
import com.example.fullchamba.fragment.DetalleTareaFragment;
import com.example.fullchamba.fragment.PerfilFragment;
import com.example.fullchamba.fragment.RegistroTareaFragment;
import com.example.fullchamba.fragment.TareaFragment;
import com.example.fullchamba.interfaces.ComunicaFragment;
import com.google.android.material.navigation.NavigationView;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ComunicaFragment {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Empleado empleado;

    View viewNavigationView;

    TextView textViewIdHeaderUser;
    TextView textViewNombreHeaderUser;
    TextView textViewApellidoHeaderUser;
    TextView textViewCelularHeaderUser;
    TextView textViewCorreorHeaderUser;

    TareaFragment tareaFragment;
    RegistroTareaFragment registroTareaFragment;
    ActualizaTareaFragment actualizaTareaFragment;
    DetalleTareaFragment detalleTareaFragment;
    PerfilFragment perfilFragment;
    ActualizaEmpleadoFragment actualizaEmpleadoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigation_view);

        viewNavigationView = navigationView.getHeaderView(0);

        try {

            int id = getIntent().getExtras().getInt("id");
            String nombre = getIntent().getExtras().getString("nombre");
            String apellido = getIntent().getExtras().getString("apellido");
            String celular = getIntent().getExtras().getString("celular");
            String correo = getIntent().getExtras().getString("correo");
            String clave = getIntent().getExtras().getString("clave");
            int estado = getIntent().getExtras().getInt("estado");
            int cargo_id = getIntent().getExtras().getInt("cargo_id");

            empleado = new Empleado(id, nombre, apellido, celular, correo, clave, estado, cargo_id);

            textViewIdHeaderUser = viewNavigationView.findViewById(R.id.textViewIdHeaderUser);
            textViewNombreHeaderUser = viewNavigationView.findViewById(R.id.textViewNombreHeaderUser);
            textViewApellidoHeaderUser = viewNavigationView.findViewById(R.id.textViewApellidoHeaderUser);
            textViewCelularHeaderUser = viewNavigationView.findViewById(R.id.textViewCelularHeaderUser);
            textViewCorreorHeaderUser = viewNavigationView.findViewById(R.id.textViewCorreorHeaderUser);

            textViewIdHeaderUser.setText(String.valueOf(empleado.getId()));
            textViewNombreHeaderUser.setText(empleado.getNombre());
            textViewApellidoHeaderUser.setText(empleado.getApellido());
            textViewCelularHeaderUser.setText(empleado.getCelular());
            textViewCorreorHeaderUser.setText(empleado.getCorreo());

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.w("exception", e.getMessage());
        }

        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, (R.string.open), (R.string.close));
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        tareaFragment = new TareaFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("empleado", empleado);

        tareaFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_principal, tareaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.inicio) {
            tareaFragment = new TareaFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("empleado", empleado);
            tareaFragment.setArguments(bundle);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_principal, tareaFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.perfil) {
            perfilFragment = new PerfilFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("empleado", empleado);
            perfilFragment.setArguments(bundle);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_principal, perfilFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.tarea) {
            tareaFragment = new TareaFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("empleado", empleado);
            tareaFragment.setArguments(bundle);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_principal, tareaFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.dashboard) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_principal, new DashboardFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.salir) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Gracias por usar Full Chamba", Toast.LENGTH_SHORT).show();
            Log.w("respuesta", "Gracias por usar Full Chamba");
        }
        return false;

    }

    @Override
    public void enviarEmpleadoRegistroTarea(Empleado empleado) {
        registroTareaFragment = new RegistroTareaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("empleado", empleado);
        registroTareaFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, registroTareaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void comunicarTareaFragment() {
        tareaFragment = new TareaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("empleado", empleado);
        tareaFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, tareaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void comunicarActualizaEmpleado() {
        actualizaEmpleadoFragment = new ActualizaEmpleadoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("empleado", empleado);
        actualizaEmpleadoFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, actualizaEmpleadoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarEmpleadoPerfil(Empleado empleado) {
        actualizarDrawerHader(empleado);

        perfilFragment = new PerfilFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("empleado", empleado);
        perfilFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, perfilFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarTareaActualizaTarea(Tarea tarea) {
        actualizaTareaFragment = new ActualizaTareaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("tarea", tarea);
        actualizaTareaFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, actualizaTareaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarTareaDetalleTarea(Tarea tarea) {
        detalleTareaFragment = new DetalleTareaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("tarea", tarea);
        detalleTareaFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, detalleTareaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void actualizarDrawerHader(Empleado empleado) {
        textViewIdHeaderUser = viewNavigationView.findViewById(R.id.textViewIdHeaderUser);
        textViewNombreHeaderUser = viewNavigationView.findViewById(R.id.textViewNombreHeaderUser);
        textViewApellidoHeaderUser = viewNavigationView.findViewById(R.id.textViewApellidoHeaderUser);
        textViewCelularHeaderUser = viewNavigationView.findViewById(R.id.textViewCelularHeaderUser);
        textViewCorreorHeaderUser = viewNavigationView.findViewById(R.id.textViewCorreorHeaderUser);

        textViewIdHeaderUser.setText(String.valueOf(empleado.getId()));
        textViewNombreHeaderUser.setText(empleado.getNombre());
        textViewApellidoHeaderUser.setText(empleado.getApellido());
        textViewCelularHeaderUser.setText(empleado.getCelular());
        textViewCorreorHeaderUser.setText(empleado.getCorreo());
    }

}
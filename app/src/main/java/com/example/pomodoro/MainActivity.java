package com.example.pomodoro;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
//import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import com.example.pomodoro.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.pomodoro.fragments.HistoryFragment;
import com.example.pomodoro.fragments.HomeFragment;
import com.example.pomodoro.fragments.TodoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavigationView;
    final HomeFragment homeFragment=new HomeFragment();
    final TodoFragment todoFragment = new TodoFragment();
    final HistoryFragment historyFragment= new HistoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = homeFragment;
                } else if (item.getItemId() == R.id.nav_todolist) {
                    selectedFragment = todoFragment;
                } else if (item.getItemId() == R.id.nav_history) {
                    selectedFragment = historyFragment;
                }
                if(selectedFragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,selectedFragment).commit();
                    return true;
                }
                return false;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar );
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
           actionBar.setTitle("Pomodoro App");

        //BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "pomodoro-database").build();

        /*BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_todolist, R.id.nav_history)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        */


        /*

        //Initialize BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Initialize NavHostFragment and NavController
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        // Set up ActionBar with NavController
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_todolist, R.id.nav_history)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Set up BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNav, navController);

        // Example of Room Database initialization
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "pomodoro-database").build();*/

        /*
        // Initialize BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        // Initialize NavController from NavHostFragment
        NavController navController = navHostFragment.getNavController();

        // Set up top-level destinations for AppBarConfiguration
         appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.historyFragment, R.id.todoFragment)
                .build();

        // Connect BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNav, navController);

        // Set up ActionBar with NavController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    // Handle Up button (Back button) properly
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();*/





    }


}


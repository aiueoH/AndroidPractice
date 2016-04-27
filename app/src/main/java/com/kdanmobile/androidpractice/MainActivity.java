package com.kdanmobile.androidpractice;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kdanmobile.androidpractice.screen.calculator.CalculatorFragment;
import com.kdanmobile.androidpractice.screen.canvas.CanvasFragment;
import com.kdanmobile.androidpractice.screen.clock.ClockFragment;
import com.kdanmobile.androidpractice.screen.imagemanipulation.ImageManipulationFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment calculatorFragment;
    private Fragment canvasFragment;
    private Fragment clockFragment;
    private Fragment imageManipulationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initFragments();
    }

    private void initFragments() {
        calculatorFragment = new CalculatorFragment();
        canvasFragment = new CanvasFragment();
        clockFragment = new ClockFragment();
        imageManipulationFragment = new ImageManipulationFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        if (id == R.id.nav_calculator) {
            fragment = calculatorFragment;
        } else if (id == R.id.nav_canvas) {
            fragment = canvasFragment;
        } else if (id == R.id.nav_clock) {
            fragment = clockFragment;
        } else if (id == R.id.nav_imageManipulation) {
            fragment = imageManipulationFragment;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout_content, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

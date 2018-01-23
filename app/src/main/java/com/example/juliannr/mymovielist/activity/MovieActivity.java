package com.example.juliannr.mymovielist.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.juliannr.mymovielist.R;
import com.example.juliannr.mymovielist.fragment.NowPlayingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.juliannr.mymovielist.utility.Constant.FragmentChooser.NOW_PLAYING;

public class MovieActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();
        fragment = NowPlayingFragment.newInstance();
        setFragment(fragment, NOW_PLAYING);
    }

    private void setFragment(Fragment fragment, String jenis) {
        toolbar.setTitle(jenis);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragment).commit();
    }

    private void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_now_playing) {
            // Handle the camera action
        } else if (id == R.id.nav_top_rated) {

        } else if (id == R.id.nav_upcoming) {

        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_exit) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

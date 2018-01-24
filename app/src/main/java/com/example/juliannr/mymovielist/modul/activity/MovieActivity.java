package com.example.juliannr.mymovielist.modul.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.juliannr.mymovielist.R;
import com.example.juliannr.mymovielist.modul.fragment.MovieFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.juliannr.mymovielist.utility.Constant.FragmentChooser.NOW_PLAYING;
import static com.example.juliannr.mymovielist.utility.Constant.FragmentChooser.POPULAR;
import static com.example.juliannr.mymovielist.utility.Constant.FragmentChooser.TOP_RATED;
import static com.example.juliannr.mymovielist.utility.Constant.FragmentChooser.UPCOMING;

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
        fragment = MovieFragment.newInstance(NOW_PLAYING);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragment).commit();
    }

    private void setFragment(Fragment fragment, String jenis) {
        toolbar.setTitle(jenis);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    private void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(NOW_PLAYING);
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
        int id = item.getItemId();
        if (id == R.id.nav_now_playing) {
            fragment = MovieFragment.newInstance(NOW_PLAYING);
            setFragment(fragment, NOW_PLAYING);
        } else if (id == R.id.nav_top_rated) {
            fragment = MovieFragment.newInstance(TOP_RATED);
            setFragment(fragment, TOP_RATED);
        } else if (id == R.id.nav_upcoming) {
            fragment = MovieFragment.newInstance(UPCOMING);
            setFragment(fragment, UPCOMING);
        } else if (id == R.id.nav_popular){
            fragment = MovieFragment.newInstance(POPULAR);
            setFragment(fragment, POPULAR);
        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_exit) {
            System.exit(0);
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}

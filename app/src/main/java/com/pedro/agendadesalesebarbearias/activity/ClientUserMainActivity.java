package com.pedro.agendadesalesebarbearias.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.adapter.PagerAdapter;
import com.pedro.agendadesalesebarbearias.fragment.ClientViewMainFragment;
import com.pedro.agendadesalesebarbearias.fragment.ClientViewScheduleFragment;

public class ClientUserMainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_user_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new ClientViewMainFragment(), getString(R.string.main_user_tab));
        pagerAdapter.addFragment(new ClientViewScheduleFragment(), getString(R.string.schedule_user_tab));
        viewPager.setAdapter(pagerAdapter);
    }
}

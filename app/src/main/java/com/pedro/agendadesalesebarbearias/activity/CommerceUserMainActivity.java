package com.pedro.agendadesalesebarbearias.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.adapter.PagerAdapter;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;
import com.pedro.agendadesalesebarbearias.fragment.CommerceViewClientsFragment;
import com.pedro.agendadesalesebarbearias.fragment.CommerceViewMainFragment;
import com.pedro.agendadesalesebarbearias.fragment.CommerceViewReportsFragment;
import com.pedro.agendadesalesebarbearias.fragment.CommerceViewScheduleFragment;

public class CommerceUserMainActivity extends AppCompatActivity {

    private Button btnDeslogar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce_user_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        btnDeslogar = findViewById( R.id.btnDeslogar );

        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseControl.signOut();
                Intent intent = new Intent( CommerceUserMainActivity.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        });

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager){
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new CommerceViewMainFragment(), getString(R.string.commerce_main_fragment_tab_title));
        pagerAdapter.addFragment(new CommerceViewScheduleFragment(), getString(R.string.commerce_schedule_fragment_tab_title));
        pagerAdapter.addFragment(new CommerceViewReportsFragment(), getString(R.string.commerce_reports_fragment_tab_title));
        pagerAdapter.addFragment(new CommerceViewClientsFragment(), getString(R.string.commerce_clients_fragment_tab_title));
        viewPager.setAdapter(pagerAdapter);
    }
}

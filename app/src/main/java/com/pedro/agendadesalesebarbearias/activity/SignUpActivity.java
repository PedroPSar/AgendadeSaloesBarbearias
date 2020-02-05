package com.pedro.agendadesalesebarbearias.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.fragment.SignUpClientFragment;

public class SignUpActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((AppCompatRadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioBtn_client:
                if (checked)
                changeFragment();
                    break;
            case R.id.radioBtn_prof:
                if (checked)

                    break;
        }
    }

    public void changeFragment(){
        SignUpClientFragment fragment = new SignUpClientFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_container, fragment, "CLIENT_FRAGMENT").commit();
    }
}

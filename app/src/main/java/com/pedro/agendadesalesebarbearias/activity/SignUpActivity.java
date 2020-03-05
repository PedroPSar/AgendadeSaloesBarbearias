package com.pedro.agendadesalesebarbearias.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pedro.agendadesalesebarbearias.fragment.SignUpCommerceFragment;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.fragment.SignUpClientFragment;

public class SignUpActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private boolean isClientFragment = true;

    private SignUpClientFragment clientFragment;
    private SignUpCommerceFragment signUpCommerceFragment;

    private static final String CLIENT_TAG = "CLIENT_FRAGMENT";
    private static final String COMMERCE_TAG = "COMMERCE_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        clientFragment = new SignUpClientFragment();
        signUpCommerceFragment = new SignUpCommerceFragment();

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, clientFragment, CLIENT_TAG);
        fragmentTransaction.commit();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((AppCompatRadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioBtn_client:
                if (checked)
                    if(!isClientFragment){
                        changeClientFragment();
                        isClientFragment = true;
                    }
                    break;
            case R.id.radioBtn_commerce:
                if (checked)
                    if(isClientFragment){
                        changeCommerceFragment();
                        isClientFragment = false;
                    }

                    break;
        }
    }

    public void changeClientFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment_container, clientFragment, CLIENT_TAG).commit();
    }

    public void changeCommerceFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment_container, signUpCommerceFragment, COMMERCE_TAG).commit();
    }

}

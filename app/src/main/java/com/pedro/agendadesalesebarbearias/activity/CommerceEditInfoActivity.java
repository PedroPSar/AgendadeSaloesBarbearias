package com.pedro.agendadesalesebarbearias.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.control.ConfigurationFirebase;
import com.pedro.agendadesalesebarbearias.control.EncoderBase64;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;
import com.pedro.agendadesalesebarbearias.model.Address;
import com.pedro.agendadesalesebarbearias.model.SalaoBarbearia;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CommerceEditInfoActivity extends AppCompatActivity {

    private static final int IMG_BG_REQUEST_CODE = 12;
    private static final int IMG_AVATAR_REQUEST_CODE = 10;
    private Uri imgUri;

    private Toolbar toolbar;
    private AppCompatImageButton btnEditBackground;
    private AppCompatImageButton btnEditLogo;
    private AppCompatEditText editTextCommerceName;
    private AppCompatEditText editTextCommerceEmail;
    private AppCompatEditText editTextCommerceTel;
    private AppCompatEditText editTextStreet;
    private AppCompatEditText editTextNumber;
    private AppCompatEditText editTextDistrict;
    private AppCompatEditText editTextCity;
    private AppCompatSpinner spinnerState;
    private String commerceState = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce_edit_info);

        getObjectsIds();
        loadSpinner();
        editTextLoadTexts();
        btnEditBackgroundClick();
        btnEditLogoClick();

        setSupportActionBar(toolbar);
    }

    // Create action bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_commerce_info_toolbar_menu, menu);
        return true;
    }

    // Set item selected click


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.editCommerceMenuBtnSave:
                saveCommerceInfo();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void getObjectsIds(){

        toolbar = findViewById(R.id.toolbar);
        btnEditBackground = findViewById(R.id.btnEditBackground);
        btnEditLogo = findViewById(R.id.btnEditLogo);
        editTextCommerceName = findViewById(R.id.txtCommerceNameEdit);
        editTextCommerceEmail = findViewById(R.id.txtCommerceEmailEdit);
        editTextCommerceTel = findViewById(R.id.txtCommerceTelephoneEdit);
        editTextStreet = findViewById(R.id.txtStreet);
        editTextNumber = findViewById(R.id.txtNumber);
        editTextDistrict = findViewById(R.id.txtDistrict);
        editTextCity = findViewById(R.id.txtCity);
        spinnerState = findViewById(R.id.spinnerState);

    }

    private void btnEditBackgroundClick(){
        btnEditBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseControl.uploadTask != null && FirebaseControl.uploadTask.isInProgress()){

                    Toast.makeText(CommerceEditInfoActivity.this, getString(R.string.toast_text_img_upload_in_progress),
                            Toast.LENGTH_SHORT).show();

                }else {
                    Intent getImageIntent = new Intent();
                    getImageIntent.setType("image/*");
                    getImageIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(getImageIntent, IMG_BG_REQUEST_CODE);
                }

            }
        });
    }

    private void btnEditLogoClick(){
        btnEditLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseControl.uploadTask != null && FirebaseControl.uploadTask.isInProgress()){

                    Toast.makeText(CommerceEditInfoActivity.this, getString(R.string.toast_text_img_upload_in_progress),
                            Toast.LENGTH_SHORT).show();

                }else {

                    // start picker to get image for cropping and then use the image in cropping activity
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(1, 1)
                            .start(CommerceEditInfoActivity.this);
                }
            }
        });
    }

    private void editTextLoadTexts(){
        FirebaseControl.setEditTextInfoInCommerceEditActivity(this, editTextCommerceName, editTextCommerceEmail,
                editTextCommerceTel, editTextStreet, editTextNumber, editTextDistrict, editTextCity, spinnerState);
    }

    private void saveCommerceInfo(){
        FirebaseAuth auth = ConfigurationFirebase.getFirebaseAuth();
        String userId = EncoderBase64.encoderBase64(auth.getCurrentUser().getEmail());

        Address address = new Address();
        address.setStreet(editTextStreet.getText().toString());
        address.setHouseNumber(editTextNumber.getText().toString());
        address.setDistrict(editTextDistrict.getText().toString());
        address.setCity(editTextCity.getText().toString());
        address.setState(commerceState);

        SalaoBarbearia commerceInfo = new SalaoBarbearia();
        commerceInfo.setId(userId);
        commerceInfo.setName(editTextCommerceName.getText().toString());
        commerceInfo.setEmail(editTextCommerceEmail.getText().toString());
        commerceInfo.setTel(editTextCommerceTel.getText().toString());
        commerceInfo.setAddress(address);

        commerceInfo.saveCommerce();

        Intent intent = new Intent(CommerceEditInfoActivity.this, CommerceUserMainActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadSpinner(){

        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Address.states);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerState.setAdapter(spinnerAdapter);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                commerceState = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_BG_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){

            imgUri = data.getData();

            FirebaseControl.uploadImgInStorage(this, FirebaseControl.COMMERCE_DB, FirebaseControl.BG_IMG_NAME, imgUri);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Log.d("teste", "crop activity result ok");
                Uri resultUri = result.getUri();
                FirebaseControl.uploadImgInStorage(this, FirebaseControl.COMMERCE_DB, FirebaseControl.AVATAR_IMG_NAME, resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Log.d("teste", "crop exception");
                Exception error = result.getError();
            }
        }

    }
}

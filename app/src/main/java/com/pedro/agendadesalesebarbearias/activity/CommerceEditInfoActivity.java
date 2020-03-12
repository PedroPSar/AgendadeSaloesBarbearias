package com.pedro.agendadesalesebarbearias.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.control.AppControl;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;

public class CommerceEditInfoActivity extends AppCompatActivity {

    private static final int IMG_REQUEST_CODE = 12;
    private Uri imgBgUri;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce_edit_info);

        getObjectsIds();
        btnEditBackgroundClick();

    }

    private void getObjectsIds(){
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
                    startActivityForResult(getImageIntent, IMG_REQUEST_CODE);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){

            imgBgUri = data.getData();
            String imgName = FirebaseControl.BG_IMG_NAME + "." + AppControl.getExtension(this, imgBgUri);

            FirebaseControl.uploadImgInStorage(this, FirebaseControl.COMMERCE_DB, imgName, imgBgUri);

        }
    }
}

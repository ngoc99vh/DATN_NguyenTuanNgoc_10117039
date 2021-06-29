package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Model.Images;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UpdateStoreActivity extends AppCompatActivity {
    private Posts posts;
    private static final String TAG = "ngocnt";
    long uuid;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button btn_upload;
    EditText edt_Name, edt_Dongxe, edt_MadeinDate, edt_Khuvuc, edt_color, edt_pirce, edt_km, edt_TT, edt_dongco;
    ImageView imgPost, imgPost2;
    Uri imgUri1;
    Uri imgUri2;
    CheckBox cbUse, cb_Nouse;
    int check = 0;
    private String condition = "";

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    private static SharedPreferences saveInfoAccount;
    private SharedPreferences.Editor editor;
    Images images;
    ArrayList<String> listImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_store);
        Intent intent = getIntent();
        posts = (Posts) intent.getSerializableExtra("sanpham");
        init();
        getData();

        mStorageRef = FirebaseStorage.getInstance().getReference("Posts");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");


        if (cbUse.isChecked()) {
            condition = cbUse.getText().toString();
            cb_Nouse.setChecked(false);
        } else {
            cbUse.setChecked(false);
            condition = cb_Nouse.getText().toString();
        }

        imgPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = 1;
                openFileChooser();
            }
        });

        imgPost2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = 2;
                openFileChooser();
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            uploadFile();
            }
        });

    }

    public void init() {
        btn_upload = findViewById(R.id.btn_upload_file_store);
        edt_Name = findViewById(R.id.edt_nameXe_store);
        edt_Dongxe = findViewById(R.id.edt_dongxe_store);
        edt_dongco = findViewById(R.id.edt_dongco_store);
        edt_color = findViewById(R.id.edt_color_store);
        edt_MadeinDate = findViewById(R.id.edt_madeinDate_store);
        edt_Khuvuc = findViewById(R.id.edt_khuvuc_store);
        edt_pirce = findViewById(R.id.edt_pirce_store);
        edt_km = findViewById(R.id.edt_km_store);
        edt_TT = findViewById(R.id.edt_themTT_store);
        edt_pirce = findViewById(R.id.edt_pirce_store);
        cbUse = findViewById(R.id.check_use_P_store);
        cb_Nouse = findViewById(R.id.check_nouse_P_store);
        imgPost = findViewById(R.id.imagePost_store);
        imgPost2 = findViewById(R.id.imagePost2_store);

    }

    @SuppressLint("SetTextI18n")
    public void getData() {
        edt_color.setText(posts.getpColor());
        edt_Dongxe.setText(posts.getpDongxe());
        edt_dongco.setText(posts.getpDongco());
        edt_Name.setText(posts.getpName());
        edt_MadeinDate.setText(posts.getpDate());
        edt_Khuvuc.setText(posts.getpKhuvuc());
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", otherSymbols);
        edt_pirce.setText(decimalFormat.format(posts.getpPice()));
        edt_km.setText(posts.getpKmuse() + "");
        edt_TT.setText(posts.getpThongtin());
        Picasso.get().load(posts.getImages().getImage1()).resize(300, 300).centerCrop().into(imgPost);
        Picasso.get().load(posts.getImages().getImage2()).resize(300, 300).centerCrop().into(imgPost2);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d(TAG, "handleMessage: " + msg.what);
            switch (msg.what) {
                case 1:
                    postFile();
                    break;
                default:
                    break;
            }
        }
    };

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            if (check == 1) {
                imgUri1 = data.getData();
                Picasso.get().load(imgUri1).resize(300, 300).centerCrop().into(imgPost);
            } else {
                imgUri2 = data.getData();
                Picasso.get().load(imgUri2).resize(300, 300).centerCrop().into(imgPost2);
            }

        }
    }


    private String getFileExtension(Uri uri) {
        ContentResolver cR = getApplicationContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (imgUri1 != null) {
            StorageReference reference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUri1));
            reference.putFile(imgUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d(TAG, "onSuccess: 1");
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.d(TAG, "onSuccess: 2");
                            listImages.add(uri.toString());
                            if (listImages.size() == 2) {
                                mHandler.sendEmptyMessage(1);
                            }
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(UpdateStoreActivity.this, "File lớn hơn 10MB", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }

        if (imgUri2 != null) {
            StorageReference reference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUri2));
            reference.putFile(imgUri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d(TAG, "onSuccess: 3");
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.d(TAG, "onSuccess: 4");
                            listImages.add(uri.toString());
                            if (listImages.size() == 2) {
                                mHandler.sendEmptyMessage(1);
                            }
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(UpdateStoreActivity.this, "File lớn hơn 10MB", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    private void postFile() {
        String id = posts.getId();
        String tenXe = edt_Name.getText().toString();
        String dongco = edt_dongco.getText().toString();
        String dongXe = edt_Dongxe.getText().toString();
        String namSX = edt_MadeinDate.getText().toString();
        String color = edt_color.getText().toString();
        String khuvuc = edt_Khuvuc.getText().toString();
        String thongtin = edt_TT.getText().toString();
        String status = "Chưa xác nhận";
        Float gia = Float.valueOf(edt_pirce.getText().toString());
        Float km = Float.valueOf(edt_km.getText().toString());
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        mDatabaseRef.orderByChild("id").equalTo(posts.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child: snapshot.getChildren()) {
                    String key= child.getKey();
                    mDatabaseRef.child(key).child("images").child("image1").setValue(listImages.get(0));
                    mDatabaseRef.child(key).child("images").child("image2").setValue(listImages.get(1));
                    mDatabaseRef.child(key).child("pColor").setValue(color);
                    mDatabaseRef.child(key).child("pCondition").setValue(condition);
                    mDatabaseRef.child(key).child("pCreateat").setValue(currentDate);
                    mDatabaseRef.child(key).child("pDate").setValue(namSX);
                    mDatabaseRef.child(key).child("pDongco").setValue(dongco);
                    mDatabaseRef.child(key).child("pDongxe").setValue(dongXe);
                    mDatabaseRef.child(key).child("pKhuvuc").setValue(khuvuc);
                    mDatabaseRef.child(key).child("pThongtin").setValue(thongtin);
                    mDatabaseRef.child(key).child("pKmuse").setValue(km);
                    mDatabaseRef.child(key).child("pPice").setValue(gia);
                    mDatabaseRef.child(key).child("pStatus").setValue(status);
                    mDatabaseRef.child(key).child("pName").setValue(tenXe);
                    startActivity(new Intent(UpdateStoreActivity.this,StoreActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
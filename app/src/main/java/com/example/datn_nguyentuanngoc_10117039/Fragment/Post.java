package com.example.datn_nguyentuanngoc_10117039.Fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Activity.LoginActivity;
import com.example.datn_nguyentuanngoc_10117039.Model.Images;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Post extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button btn_choose, btn_upload, btn_choose2;
    EditText edt_fileName;
    ImageView imgPost, imgPost2;
    Uri imgUri;
    Uri imgUri2;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    private static SharedPreferences saveInfoAccount;
    private SharedPreferences.Editor editor;
    String userName = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        // ánh xạ
        btn_choose = view.findViewById(R.id.btn_choose_file);
        btn_choose2 = view.findViewById(R.id.btn_choose_file2);
        btn_upload = view.findViewById(R.id.btn_upload_file);
        edt_fileName = view.findViewById(R.id.edt_file_name);
        imgPost = view.findViewById(R.id.imagePost);
        imgPost2 = view.findViewById(R.id.imagePost2);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        // click
        saveInfoAccount = getContext().getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
         userName = saveInfoAccount.getString("userName", null);
        if (!TextUtils.isEmpty(userName)) {
            btn_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFileChooser();
                }
            });

            btn_choose2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFileChooser();
                }
            });
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK
                && data != null && data.getData() != null) {
            imgUri = data.getData();
            Picasso.with(getActivity()).load(imgUri).into(imgPost);
        }
    }



    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (imgUri != null) {
            StorageReference reference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUri));
            reference.putFile(imgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Images images = new Images(taskSnapshot.getUploadSessionUri().toString());
                            Posts post = new Posts(edt_fileName.getText().toString().trim(),images);
                            mDatabaseRef.child(userName).setValue(post);
                            Toast.makeText(getActivity(), "Upload thành công", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
    public void checkData() {

    }
}
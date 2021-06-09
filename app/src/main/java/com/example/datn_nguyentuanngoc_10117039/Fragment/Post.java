package com.example.datn_nguyentuanngoc_10117039.Fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Post extends Fragment {
    private static final String TAG = "locnt";

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button btn_choose, btn_upload, btn_choose2;
    EditText edt_fileName;
    ImageView imgPost, imgPost2;
    Uri imgUri1;
    Uri imgUri2;
    int check = 0;
    private int counter;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    private static SharedPreferences saveInfoAccount;
    private SharedPreferences.Editor editor;
    String userName = "";
    Images images;

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

        images = new Images();
        counter = 0;
        // click
        saveInfoAccount = getContext().getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
        userName = saveInfoAccount.getString("userName", null);
        if (!TextUtils.isEmpty(userName)) {
            btn_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check = 1;
                    openFileChooser();
                }
            });

            btn_choose2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check = 2;
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

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d(TAG, "handleMessage: "+msg.what);
            switch (msg.what){
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
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK
                && data != null && data.getData() != null) {

            if(check == 1){
                imgUri1 = data.getData();
                Picasso.with(getActivity()).load(imgUri1).into(imgPost);
            }else {
                imgUri2 = data.getData();
                Picasso.with(getActivity()).load(imgUri2).into(imgPost2);
            }

        }
    }



    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
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
                            images.addImage(uri.toString());
                            if (images.getNumberImages() == 2){
                                mHandler.sendEmptyMessage(1);
                            }
                        }
                    });
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
                            images.addImage(uri.toString());
                            if (images.getNumberImages() == 2){
                                mHandler.sendEmptyMessage(1);
                            }
                        }
                    });
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

    private void postFile(){
        Posts post = new Posts(userName,images);
        Log.d(TAG, "images: "+images.getNumberImages());
        String uploadId = mDatabaseRef.push().getKey();
        assert uploadId != null;
        mDatabaseRef.child(uploadId).setValue(post);


        Toast.makeText(getActivity(), "Upload thành công", Toast.LENGTH_SHORT).show();
    }
}
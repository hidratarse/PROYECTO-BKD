package com.example.proyecto_bkd;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ImageRepository {
    private String PROFILE_PICTURES = "pfp/";
    private String IMAGE_FORMAT = ".jpg";
    private StorageReference storageRef;
    private static ImageRepository instance;

    public interface UploadImageCallback {
        void onSuccess(String downloadUrl);
        void onFailure(Exception exception);
    }

    public ImageRepository(){
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    public static ImageRepository getInstance(){
        if (instance == null) {
            instance = new ImageRepository();
        }
        return instance;
    }

    public void uploadImage(Uri imageUri, UploadImageCallback listener) {
        String imageName = System.currentTimeMillis() + IMAGE_FORMAT;
        StorageReference fileRef = storageRef.child(PROFILE_PICTURES + imageName);
        UploadTask uploadTask = fileRef.putFile(imageUri);

        uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }
            return fileRef.getDownloadUrl();
        }).addOnSuccessListener(uri -> {
            String downloadUrl = uri.toString();
            listener.onSuccess(downloadUrl);
        }).addOnFailureListener(e -> {
            listener.onFailure(e);
        });
    }

}

package com.example.proyecto_bkd.perfiles.api;

import androidx.lifecycle.MutableLiveData;

import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PerfilesRepository {
    private static PerfilesRepository instance;
    private static final String NOMBRE_COLECCION = "perfiles";
    private final CollectionReference coleccion;
    private MutableLiveData<List<Perfil>> listaPerfilesLivedata;
    private MutableLiveData<Perfil> perfilLiveData;

    public interface UploadPerfilesCallback{
        void onSuccess(String id);
        void onFailure(Exception exception);
    }

    public PerfilesRepository() {
        listaPerfilesLivedata = new MutableLiveData<>();
        perfilLiveData = new MutableLiveData<>();
        coleccion = FirebaseFirestore.getInstance().collection(NOMBRE_COLECCION);
    }

    //singleton
    public static PerfilesRepository getInstance() {
        if (instance == null) {
            instance = new PerfilesRepository();
        }
        return instance;
    }

    public void getPerfilesPorDueño(String email) {
        coleccion.whereEqualTo("email", email).get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Perfil> listaPerfiles = new ArrayList<>();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Perfil perfil = document.toObject(Perfil.class);
                perfil.setId(document.getId());
                listaPerfiles.add(perfil);
            }
            listaPerfilesLivedata.postValue(listaPerfiles);
        }).addOnFailureListener(e -> {
            listaPerfilesLivedata.postValue(null);
        });
    }

    //recupera solo un perfil con el id
    public void getPerfil(String idPerfil) {
        coleccion.document(idPerfil).get().addOnSuccessListener(documentSnapshot -> {
            Perfil perfil = documentSnapshot.toObject(Perfil.class);
            perfil.setId(documentSnapshot.getId());
            perfilLiveData.postValue(perfil);
        }).addOnFailureListener(e -> {
            perfilLiveData.postValue(null);
        });
    }

    public void insertarPerfil(Perfil perfil, UploadPerfilesCallback listener) {
        coleccion.add(perfil).addOnSuccessListener(documentReference -> {
            perfilLiveData.postValue(perfil);
            listener.onSuccess(documentReference.getId());
        }).addOnFailureListener(e -> {
            perfilLiveData.postValue(null);
            listener.onFailure(e);
        });
    }

    public void modificarPerfil(String idPerfil, Perfil nuevoPerfil) {
        coleccion.document(idPerfil).set(nuevoPerfil).addOnSuccessListener(aVoid -> {
            nuevoPerfil.setId(idPerfil);
            perfilLiveData.postValue(nuevoPerfil);
        }).addOnFailureListener(e -> {
            perfilLiveData.postValue(null);
        });
    }

    public void  eliminarPerfil(String idPerfil){
        coleccion.document(idPerfil).delete().addOnSuccessListener(aVoid ->{
        });
    }

    public MutableLiveData<List<Perfil>> getListaPerfilesLivedata() {
        return listaPerfilesLivedata;
    }

    public MutableLiveData<Perfil> getPerfilLiveData() {
        return perfilLiveData;
    }
}

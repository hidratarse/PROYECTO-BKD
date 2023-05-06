package com.example.proyecto_bkd.perfiles;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_bkd.ImageRepository;
import com.example.proyecto_bkd.perfiles.api.PerfilesRepository;
import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.example.proyecto_bkd.utils.DefaultPfp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class PerfilesViewModel extends ViewModel {
    private MutableLiveData<List<Perfil>> perfilesData;
    private MutableLiveData<Perfil> perfilLivedata;
    private PerfilesRepository perfilesRepository;
    private ImageRepository imageRepository;

    public void init() {
        perfilesRepository = PerfilesRepository.getInstance();
        imageRepository = ImageRepository.getInstance();
        perfilesData = perfilesRepository.getListaPerfilesLivedata();
        perfilLivedata = perfilesRepository.getPerfilLiveData();
    }

    public void getPerfiles(String email) {
        perfilesRepository.getPerfilesPorDueño(email);
    }

    public void insertarPerfil(Perfil perfil, Uri pfpUri){
        if (pfpUri == null) {
            perfil.setPfpImg(DefaultPfp.getRandomDefaultPfp());
            perfilesRepository.insertarPerfil(perfil);
        }else {
            imageRepository.uploadImage(pfpUri, new ImageRepository.UploadImageCallback() {

                @Override
                public void onSuccess(String downloadUrl) {
                    perfil.setPfpImg(downloadUrl);
                    perfilesRepository.insertarPerfil(perfil);
                    getPerfiles(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                }

                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                    perfilesRepository.insertarPerfil(perfil);
                }
            });
        }
    }

    public void getPerfil(String id){
        perfilesRepository.getPerfil(id);
    }

    public void modificarPerfil(String id, Perfil nuevoPerfil, Uri pfpUri){
        if (pfpUri == null) {
            perfilesRepository.modificarPerfil(id, nuevoPerfil);
        }else {
            imageRepository.uploadImage(pfpUri, new ImageRepository.UploadImageCallback() {

                @Override
                public void onSuccess(String downloadUrl) {
                    nuevoPerfil.setPfpImg(downloadUrl);
                    perfilesRepository.modificarPerfil(id, nuevoPerfil);
                    getPerfiles(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                }

                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                    perfilesRepository.modificarPerfil(id, nuevoPerfil);
                }
            });
        }
    }

    public void eliminarPerfil(String id){
        perfilesRepository.eliminarPerfil(id);
    }

    public void clear(){
        perfilLivedata.postValue(new Perfil(" ", "0","0","0"));
    }

    public LiveData<List<Perfil>> getPerfilesLivedata() {
        return perfilesData;
    }

    public LiveData<Perfil> getPerfilLivedata() {
        return perfilLivedata;
    }
}

package com.example.proyecto_bkd.perfiles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_bkd.perfiles.api.PerfilesRepository;
import com.example.proyecto_bkd.perfiles.data.Perfil;

import java.util.List;

public class PerfilesViewModel extends ViewModel {
    private MutableLiveData<List<Perfil>> perfilesData;
    private MutableLiveData<Perfil> perfilLivedata;
    private PerfilesRepository perfilesRepository;

    public void init() {
        perfilesRepository = PerfilesRepository.getInstance();
        perfilesData = perfilesRepository.getListaPerfilesLivedata();
        perfilLivedata = perfilesRepository.getPerfilLiveData();
    }

    public void getPerfiles(String email) {
        perfilesRepository.getPerfilesPorDueño(email);
    }

    public void insertarPerfil(Perfil perfil){
        perfilesRepository.insertarPerfil(perfil);
    }

    public void getPerfil(String id){
        perfilesRepository.getPerfil(id);
    }

    public void modificarPerfil(String id, Perfil nuevoPerfil){
        perfilesRepository.modificarPerfil(id, nuevoPerfil);
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

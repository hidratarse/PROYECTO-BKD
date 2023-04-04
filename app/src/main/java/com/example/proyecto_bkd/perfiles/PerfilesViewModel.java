package com.example.proyecto_bkd.perfiles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_bkd.perfiles.api.PerfilesRepository;
import com.example.proyecto_bkd.perfiles.data.Perfil;

import java.util.List;

public class PerfilesViewModel extends ViewModel {
    private MutableLiveData<List<Perfil>> perfilesData;
    private PerfilesRepository perfilesRepository;

    public void init() {
        perfilesRepository = PerfilesRepository.getInstance();
        perfilesData = perfilesRepository.getListaPerfilesLivedata();
    }

    public void getPerfiles(String email) {
        perfilesRepository.getPerfilesPorDueño(email);
    }

    public LiveData<List<Perfil>> getPerfilesLivedata() {
        return perfilesData;
    }
}

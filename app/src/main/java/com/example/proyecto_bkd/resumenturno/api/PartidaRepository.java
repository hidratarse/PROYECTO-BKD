package com.example.proyecto_bkd.resumenturno.api;

import androidx.lifecycle.MutableLiveData;

import com.example.proyecto_bkd.resumenturno.data.Partidas;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PartidaRepository {
    private static PartidaRepository instance;
    private static final String NOMBRE_COLECCION = "partidas";
    private final CollectionReference coleccion;
    private MutableLiveData<List<Partidas>> listaPartidasLivedata;
    private MutableLiveData<Partidas> partidasLiveData;

    public PartidaRepository() {
        listaPartidasLivedata = new MutableLiveData<>();
        partidasLiveData = new MutableLiveData<>();
        coleccion = FirebaseFirestore.getInstance().collection(NOMBRE_COLECCION);
    }
}

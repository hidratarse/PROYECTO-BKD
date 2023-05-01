package com.example.proyecto_bkd.resumenturno.api;

import android.provider.Telephony;

import androidx.lifecycle.MutableLiveData;

import com.example.proyecto_bkd.perfiles.api.PerfilesRepository;
import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.example.proyecto_bkd.resumenturno.data.Partidas;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
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
    //singleton
    public static PartidaRepository getInstance() {
        if (instance == null) {
            instance = new PartidaRepository();
        }
        return instance;
    }

    public void getPartidasPorDueño(String email) {
        coleccion.whereEqualTo("email", email).get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Partidas> listaPartidas = new ArrayList<>();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Partidas partida = document.toObject(Partidas.class);
                partida.setIdPartida(document.getId());
                listaPartidas.add(partida);
            }
            listaPartidasLivedata.postValue(listaPartidas);
        }).addOnFailureListener(e -> {
            listaPartidasLivedata.postValue(null);
        });
    }

    //recupera solo un perfil con el id
    public void getPartida(String idPartida) {
        coleccion.document(idPartida).get().addOnSuccessListener(documentSnapshot -> {
            Partidas partidas = documentSnapshot.toObject(Partidas.class);
            partidas.setIdPartida(documentSnapshot.getId());
            partidasLiveData.postValue(partidas);
        }).addOnFailureListener(e -> {
            partidasLiveData.postValue(null);
        });
    }

    public void insertarPartida(Partidas partida) {
        coleccion.add(partida).addOnSuccessListener(documentReference -> {
            partidasLiveData.postValue(partida);
        }).addOnFailureListener(e -> {
            partidasLiveData.postValue(null);
        });
    }
    public void  eliminarPartida(String idPartida){
        coleccion.document(idPartida).delete().addOnSuccessListener(aVoid ->{
        });
    }
    public MutableLiveData<List<Partidas>> getListaPartidasLivedata() {
        return listaPartidasLivedata;
    }

    public MutableLiveData<Partidas> getPartidasLivedata() {
        return partidasLiveData;
    }
}

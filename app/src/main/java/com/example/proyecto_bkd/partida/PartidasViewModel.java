package com.example.proyecto_bkd.partida;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_bkd.partida.api.PartidaRepository;
import com.example.proyecto_bkd.partida.data.Partidas;

import java.util.List;

public class PartidasViewModel extends ViewModel {
    private MutableLiveData<List<Partidas>> partidasData;
    private MutableLiveData<Partidas> partidasLiveData;
    private PartidaRepository partidaRepository;

    public void init() {
        partidaRepository = PartidaRepository.getInstance();
        partidasData = partidaRepository.getListaPartidasLivedata();
        partidasLiveData = partidaRepository.getPartidasLivedata();
    }

    public void getPartidas(String email) {partidaRepository.getPartidasPorDueño(email);}

    public void insertarPartida(Partidas partidas){
        partidaRepository.insertarPartida(partidas);
    }

    public void getPartida(String idPartida){
        partidaRepository.getPartida(idPartida);
    }

    public void eliminarPartida(String idPartida){
        partidaRepository.eliminarPartida(idPartida);
    }

    public LiveData<List<Partidas>> getPartidasLivedata() {
        return partidasData;
    }

    public LiveData<Partidas> getPartidaLivedata() {
        return partidasLiveData;
    }
}

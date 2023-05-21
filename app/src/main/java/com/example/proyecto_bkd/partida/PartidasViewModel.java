package com.example.proyecto_bkd.partida;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_bkd.ImageRepository;
import com.example.proyecto_bkd.partida.api.PartidaRepository;
import com.example.proyecto_bkd.partida.data.Partidas;

import java.util.List;

public class PartidasViewModel extends ViewModel {
    private MutableLiveData<List<Partidas>> partidasData;
    private MutableLiveData<Partidas> partidasLiveData;
    private PartidaRepository partidaRepository;
    private ImageRepository imageRepository;

    public void init() {
        partidaRepository = PartidaRepository.getInstance();
        partidasData = partidaRepository.getListaPartidasLivedata();
        partidasLiveData = partidaRepository.getPartidasLivedata();
        imageRepository = ImageRepository.getInstance();
    }

    public void getPartidas(String email) {partidaRepository.getPartidasPorDueño(email);}

    public void insertarPartida(Partidas partidas, Uri uri){
        if (uri == null) {
            partidaRepository.insertarPartida(partidas, new PartidaRepository.UploadPartidasCallback() {
                @Override
                public void onSuccess(String id) {
                    partidas.setIdPartida(id);
                    partidaRepository.modificarPartida(id, partidas);
                }

                @Override
                public void onFailure(Exception exception) {

                }
            });
        }else {
            imageRepository.uploadImage(uri, new ImageRepository.UploadImageCallback() {
                @Override
                public void onSuccess(String downloadUrl) {
                    partidas.setFotoPartida(downloadUrl);
                    partidaRepository.insertarPartida(partidas, new PartidaRepository.UploadPartidasCallback() {
                        @Override
                        public void onSuccess(String id) {
                            partidas.setIdPartida(id);
                            partidaRepository.modificarPartida(id, partidas);
                        }

                        @Override
                        public void onFailure(Exception exception) {

                        }
                    });
                }

                @Override
                public void onFailure(Exception exception) {
                    partidaRepository.insertarPartida(partidas, new PartidaRepository.UploadPartidasCallback() {
                        @Override
                        public void onSuccess(String id) {
                            partidas.setIdPartida(id);
                            partidaRepository.modificarPartida(id, partidas);
                        }

                        @Override
                        public void onFailure(Exception exception) {

                        }
                    });
                }
            });
        }
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

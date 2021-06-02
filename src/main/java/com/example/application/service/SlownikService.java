package com.example.application.service;


import com.example.application.model.SlownikOld;
import org.springframework.stereotype.Service;

@Service
public class SlownikService {

    public SlownikService(){

    }

    public SlownikOld slownikZTablicy(String[] tablica){
        SlownikOld slownik = new SlownikOld();
        slownik.setPierwszy((tablica[0].isEmpty())?-1:Integer.parseInt(tablica[0]));
        slownik.setDrugi((tablica[1].isEmpty())?-1:Integer.parseInt(tablica[1]));
        slownik.setTrzeci((tablica[2].isEmpty())?-1:Integer.parseInt(tablica[2]));
        slownik.setCzwarty((tablica[3].isEmpty())?-1:Integer.parseInt(tablica[3]));
        slownik.setPiaty((tablica[4].isEmpty())?-1:Integer.parseInt(tablica[4]));
        slownik.setSzosty((tablica[5].isEmpty())?-1:Integer.parseInt(tablica[5]));
        return slownik;
    }
}

package it.paper.objects;

import lombok.Getter;

public class Prenotazione {

    @Getter private String nome;
    @Getter private int numeroOmbrellone;

    public Prenotazione(String nome, int numeroOmbrellone) {
        this.nome = nome;
        this.numeroOmbrellone = numeroOmbrellone;
    }
}

package ar.edu.unahur.obj2.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;
import ar.edu.unahur.obj2.observador.ObservadorEnergia;



public class Bateria {
    
    // reserva admitida fisica, llega hasta 5000 kw
    public static final Double LimiteDeLaReserva = (double) -5000;



    private final String id;
    private Double nivelEnergia;
    private final List<ObservadorEnergia> observadores = new ArrayList<>();

    public Bateria(String id, int nivelEnergiaInicial) {
        this.id = id;
        this.nivelEnergia = (double) nivelEnergiaInicial;
    }
    
    public String getId() {
        return id;
    }

    public Double getNivelEnergia() {
        return nivelEnergia;
    }

    // sube la energia, creo
    public void cargar(Double kwh) {
        nivelEnergia += kwh;
        notificarObservadores(TipoOperacion.carga, kwh);
    }

    // reduce el nivel de energia
    public void consumir(Double kwh) throws LimiteReservaExcedidoException {
        Double nuevoNivel = nivelEnergia - kwh;
        if(nuevoNivel < LimiteDeLaReserva) {
            throw new LimiteReservaExcedidoException(this, kwh, nuevoNivel);
        }
        nivelEnergia = nuevoNivel;
        notificarObservadores(TipoOperacion.carga, kwh);

    }

    public void suscribir(ObservadorEnergia observador) {
        observadores.add(observador);
    }

    public void desuscribir(ObservadorEnergia observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores(TipoOperacion tipo, Double kwh) {
        // recorre la lista de interesados, creo

        for(ObservadorEnergia observador : observadores) {
            observador.actualizar(this, tipo, kwh);
        }
    }
}
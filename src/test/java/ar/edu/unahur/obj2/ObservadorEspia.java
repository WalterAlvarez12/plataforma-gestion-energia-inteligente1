package ar.edu.unahur.obj2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.model.TipoOperacion;
import ar.edu.unahur.obj2.observador.ObservadorEnergia;

public class ObservadorEspia implements ObservadorEnergia {


    // QUISE HACER UN OBSERVADOR ESPIA PARA Q NO TIFIQUE, PERO NO LLEGO ES LA ULTIMAAAAAAAAAAAAAAAAAAAAA, ME TENGO QUE IR, NOSE SI LO HICE BIEN
    // PORQUE ME DEJO E ANDAR ESTO
    public static class Notificacion {
        public final String bateriaId;
        public final TipoOperacion tipo;
        public final double kwh;

        Notificacion(String bateriaId, TipoOperacion tipo, double kwh) {
            this.bateriaId = bateriaId;
            this.tipo = tipo;
            this.kwh = kwh;
        }
    }

    private final List<Notificacion> notificaciones = new ArrayList<>();

    @Override
    public void actualizar1(Bateria bateria, TipoOperacion tipo, double kwh) {
        notificaciones.add(new Notificacion(bateria.getId(), tipo, kwh));
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public int cantidadNotificaciones() {
        return notificaciones.size();
    }

    @Override
    public void actualizar(Bateria bateria, TipoOperacion tipo, Double kwh) {
       
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public void actualizar(Bateria bateria, TipoOperacion tipo, double kwh) {
       
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }
}

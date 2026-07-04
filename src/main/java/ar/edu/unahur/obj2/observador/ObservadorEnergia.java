package ar.edu.unahur.obj2.observador;

import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.model.TipoOperacion;

/**
 * ObservadorEnergia
 */
public interface ObservadorEnergia {
    void actualizar(Bateria bateria, TipoOperacion tipo, Double kwh);

    void actualizar(Bateria bateria, TipoOperacion tipo, double kwh);

    void actualizar1(Bateria bateria, TipoOperacion tipo, double kwh);
}

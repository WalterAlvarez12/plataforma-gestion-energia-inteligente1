package ar.edu.unahur.obj2.observador;

import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.model.TipoOperacion;


public class AlarmaReservaCritica implements ObservadorEnergia {

    @Override
    public void actualizar(Bateria bateria, TipoOperacion tipo, double kwh) {
        if (bateria.getNivelEnergia() < 0) {
            System.out.printf("[ALARMA] La batería '%s' quedó por debajo del nivel de tolerancia (0 kWh). " +
                            "Nivel actual: %.2f kWh. Usando reserva física.%n",
                    bateria.getId(), bateria.getNivelEnergia());
        }
    }

    @Override
    public void actualizar(Bateria bateria, TipoOperacion tipo, Double kwh) {
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }
}

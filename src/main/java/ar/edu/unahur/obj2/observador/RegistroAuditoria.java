package ar.edu.unahur.obj2.observador;

import java.time.LocalDateTime;

import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.model.TipoOperacion;

public class RegistroAuditoria implements ObservadorEnergia {

    @Override
    public void actualizar(Bateria bateria, TipoOperacion tipo, double kwh) {
        System.out.printf("[AUDITORIA] %s | Bateria: %s | Operacion: %s | Cantidad: %.2f kWh | Nivel resultante: %.2f kWh%n",
                LocalDateTime.now(), bateria.getId(), tipo, kwh, bateria.getNivelEnergia());
    }

    @Override
    public void actualizar(Bateria bateria, TipoOperacion tipo, Double kwh) {
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public void actualizar1(Bateria bateria, TipoOperacion tipo, double kwh) {

        throw new UnsupportedOperationException("Unimplemented method 'actualizar1'");
    }
}

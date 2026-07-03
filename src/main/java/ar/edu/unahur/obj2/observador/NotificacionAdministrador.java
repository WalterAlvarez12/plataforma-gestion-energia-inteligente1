package ar.edu.unahur.obj2.observador;

import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.model.TipoOperacion;

public class NotificacionAdministrador implements ObservadorEnergia {

    private final String bateriaId;
    private final String responsable;

    public NotificacionAdministrador(String bateriaId, String responsable) {
        this.bateriaId = bateriaId;
        this.responsable = responsable;
    }

    @Override
    public void actualizar(Bateria bateria, TipoOperacion tipo, double kwh) {
        if (!bateria.getId().equals(bateriaId)) {
            return; // solo le interesan los eventos de su propia batería
        }
        String verbo = (tipo == TipoOperacion.carga) ? "cargado" : "consumido";
        System.out.printf("[NOTIFICACION -> %s] Se han %s %.2f kWh en su batería '%s'.%n",
                responsable, verbo, kwh, bateria.getId());
    }

    @Override
    public void actualizar(Bateria bateria, TipoOperacion tipo, Double kwh) {
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }
}


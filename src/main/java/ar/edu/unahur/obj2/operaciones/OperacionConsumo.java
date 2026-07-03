package ar.edu.unahur.obj2.operaciones;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;
import ar.edu.unahur.obj2.model.Bateria;

public class OperacionConsumo implements OperacionEnergia {

    private final Bateria bateria;
    private final double kwh;
    private boolean ejecutada = false;

    public OperacionConsumo(Bateria bateria, double kwh) {
        if (kwh <= 0) {
            // Unchecked: uso indebido de la API, no se obliga a capturarlo.
            throw new IllegalArgumentException(
                    "La cantidad de energía a consumir debe ser mayor a 0 kWh. Valor recibido: " + kwh);
        }
        this.bateria = bateria;
        this.kwh = kwh;
    }

    @Override
    public void ejecutar() throws LimiteReservaExcedidoException {
        bateria.consumir(kwh);
        ejecutada = true;
    }

    @Override
    public void deshacer() {
        if (!ejecutada) {
            return;
        }
        // Revertir un consumo equivale a cargar la misma cantidad; cargar nunca falla.
        bateria.cargar(kwh);
        ejecutada = false;
    }
}

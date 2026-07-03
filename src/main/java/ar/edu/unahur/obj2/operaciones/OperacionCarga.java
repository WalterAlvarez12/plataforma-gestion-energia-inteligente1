package ar.edu.unahur.obj2.operaciones;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;
import ar.edu.unahur.obj2.model.Bateria;

public class OperacionCarga implements OperacionEnergia {

    private final Bateria bateria;
    private final double kwh;
    private boolean ejecutada = false;

    public OperacionCarga(Bateria bateria, double kwh) {
        if (kwh <= 0) {
            // Unchecked: es un error de programación del cliente, no debe obligar a capturarlo.
            throw new IllegalArgumentException(
                    "La cantidad de energía a cargar debe ser mayor a 0 kWh. Valor recibido: " + kwh);
        }
        this.bateria = bateria;
        this.kwh = kwh;
    }

    @Override
    public void ejecutar() {
        bateria.cargar(kwh);
        ejecutada = true;
    }

    @Override
    public void deshacer() {
        if (!ejecutada) {
            return;
        }
        try {
            // Revertir una carga equivale a consumir la misma cantidad.
            bateria.consumir(kwh);
        } catch (LimiteReservaExcedidoException e) {
            // No debería ocurrir nunca: deshacer una carga que se acaba de aplicar
            // no puede perforar el límite de reserva. Si pasa, es un estado inconsistente.
            throw new IllegalStateException("No fue posible deshacer la operación de carga.", e);
        }
        ejecutada = false;
    }
}

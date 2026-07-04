package ar.edu.unahur.obj2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;
import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.operaciones.OperacionCarga;
import ar.edu.unahur.obj2.operaciones.OperacionConsumo;

class OperacionesTest {

    private Bateria bateria;

    @BeforeEach
    void setUp() {
        bateria = new Bateria("BAT-01", 1000);
    }

    // ---------- OperacionCarga ----------

    @Test
    void operacionCargaConValorCeroLanzaExcepcionUncheckedEnElConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new OperacionCarga(bateria, 0));
    }

    @Test
    void operacionCargaConValorNegativoLanzaExcepcionUncheckedEnElConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new OperacionCarga(bateria, -10));
    }

    @Test
    void operacionCargaEjecutarIncrementaElNivel() {
        OperacionCarga carga = new OperacionCarga(bateria, 500);
        carga.ejecutar();
        assertEquals(1500, bateria.getNivelEnergia());
    }

    @Test
    void operacionCargaDeshacerRevierteElIncremento() {
        OperacionCarga carga = new OperacionCarga(bateria, 500);
        carga.ejecutar();
        carga.deshacer();
        assertEquals(1000, bateria.getNivelEnergia());
    }

    @Test
    void operacionCargaDeshacerSinHaberEjecutadoNoHaceNada() {
        OperacionCarga carga = new OperacionCarga(bateria, 500);
        carga.deshacer(); // no debería lanzar ni modificar nada
        assertEquals(1000, bateria.getNivelEnergia());
    }

    @Test
    void operacionCargaDeshacerDosVecesSoloRevierteUnaVez() {
        OperacionCarga carga = new OperacionCarga(bateria, 500);
        carga.ejecutar();
        carga.deshacer();
        carga.deshacer(); // segunda vez: la operación ya no está "ejecutada", no debe volver a afectar el nivel
        assertEquals(1000, bateria.getNivelEnergia());
    }

    // ---------- OperacionConsumo ----------

    @Test
    void operacionConsumoConValorCeroLanzaExcepcionUncheckedEnElConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new OperacionConsumo(bateria, 0));
    }

    @Test
    void operacionConsumoEjecutarReduceElNivel() throws LimiteReservaExcedidoException {
        OperacionConsumo consumo = new OperacionConsumo(bateria, 300);
        consumo.ejecutar();
        assertEquals(700, bateria.getNivelEnergia());
    }

    @Test
    void operacionConsumoQueSuperaElLimiteDeReservaLanzaExcepcionChecked() {
        OperacionConsumo consumo = new OperacionConsumo(bateria, 999999);
        assertThrows(LimiteReservaExcedidoException.class, consumo::ejecutar);
        assertEquals(1000, bateria.getNivelEnergia()); // no debe haber cambiado
    }

    @Test
    void operacionConsumoDeshacerRevierteLaReduccion() throws LimiteReservaExcedidoException {
        OperacionConsumo consumo = new OperacionConsumo(bateria, 300);
        consumo.ejecutar();
        consumo.deshacer();
        assertEquals(1000, bateria.getNivelEnergia());
    }
}

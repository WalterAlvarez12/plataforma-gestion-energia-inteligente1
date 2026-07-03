package ar.edu.unahur.obj2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;
import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.operaciones.OperacionCarga;
import ar.edu.unahur.obj2.operaciones.OperacionConsumo;
import ar.edu.unahur.obj2.operaciones.Rutina;

class RutinaTest {

    private Bateria bateria;

    @BeforeEach
    void setUp() {
        bateria = new Bateria("BAT-01", 1000);
    }

    @Test
    void ejecutaTodasLasOperacionesEnOrdenCuandoNingunaFalla() throws LimiteReservaExcedidoException {
        Rutina rutina = new Rutina("nocturna");
        rutina.agregarOperacion(new OperacionCarga(bateria, 200));   // 1200
        rutina.agregarOperacion(new OperacionConsumo(bateria, 500)); // 700

        rutina.ejecutar();

        assertEquals(700, bateria.getNivelEnergia());
    }

    @Test
    void vaciaLasOperacionesPendientesLuegoDeEjecutarConExito() throws LimiteReservaExcedidoException {
        Rutina rutina = new Rutina("nocturna");
        rutina.agregarOperacion(new OperacionCarga(bateria, 200));

        rutina.ejecutar();

        assertTrue(rutina.getOperacionesPendientes().isEmpty());
    }

    @Test
    void siUnaOperacionFallaSeRevierteSoloLoEjecutadoEnEsaRutinaYSePropagaLaExcepcion() {
        Rutina rutina = new Rutina("nocturna");
        rutina.agregarOperacion(new OperacionConsumo(bateria, 500)); // se ejecuta: 500
        rutina.agregarOperacion(new OperacionCarga(bateria, 100));   // se ejecuta: 600
        rutina.agregarOperacion(new OperacionConsumo(bateria, 10000)); // falla: 600-10000 < -5000

        assertThrows(LimiteReservaExcedidoException.class, rutina::ejecutar);

        // el nivel debe haber vuelto exactamente al estado previo a la rutina
        assertEquals(1000, bateria.getNivelEnergia());
    }
    }
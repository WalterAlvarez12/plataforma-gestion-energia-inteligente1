package ar.edu.unahur.obj2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;
import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.model.TipoOperacion;

class BateriaTest {

    private Bateria bateria;

    @BeforeEach
    void setUp() {
        bateria = new Bateria("BAT-01", 1000);
    }

    @Test
    void nivelInicialEsElRecibidoEnElConstructor() {
        assertEquals(1000, bateria.getNivelEnergia());
    }

    @Test
    void cargarIncrementaElNivelDeEnergia() {
        bateria.cargar(500);
        assertEquals(1500, bateria.getNivelEnergia());
    }

    @Test
    void consumirDentroDelRangoPermitidoReduceElNivel() throws LimiteReservaExcedidoException {
        bateria.consumir(300);
        assertEquals(700, bateria.getNivelEnergia());
    }

    @Test
    void consumirHastaExactamenteElLimiteDeReservaEsValido() throws LimiteReservaExcedidoException {
        // 1000 - 6000 = -5000, que es el límite exacto (inclusive)
        bateria.consumir(6000);
        assertEquals(-5000, bateria.getNivelEnergia());
    }

    @Test
    void consumirMasAlaDelLimiteDeReservaLanzaExcepcionChecked() {
        // 1000 - 6001 = -5001, un kWh más allá del límite
        assertThrows(LimiteReservaExcedidoException.class, () -> bateria.consumir(6001));
        // y el nivel no debe haberse modificado
        assertEquals(1000, bateria.getNivelEnergia());
    }

    @Test
    void cargarNotificaATodosLosObservadoresSuscriptos() {
        ObservadorEspia espia1 = new ObservadorEspia();
        ObservadorEspia espia2 = new ObservadorEspia();
        bateria.suscribir(espia1);
        bateria.suscribir(espia2);

        bateria.cargar(250);

        assertEquals(1, espia1.cantidadNotificaciones());
        assertEquals(1, espia2.cantidadNotificaciones());
        ObservadorEspia.Notificacion n = espia1.getNotificaciones().get(0);
        assertEquals("BAT-01", n.bateriaId);
        assertEquals(TipoOperacion.carga, n.tipo);
        assertEquals(250, n.kwh);
    }

    @Test
    void consumirNotificaConTipoConsumo() throws LimiteReservaExcedidoException {
        ObservadorEspia espia = new ObservadorEspia();
        bateria.suscribir(espia);

        bateria.consumir(100);

        assertEquals(1, espia.cantidadNotificaciones());
        assertEquals(TipoOperacion.consumo, espia.getNotificaciones().get(0).tipo);
    }

    @Test
    void consumirQueFallaPorLimiteDeReservaNoNotificaAObservadores() {
        ObservadorEspia espia = new ObservadorEspia();
        bateria.suscribir(espia);

        assertThrows(LimiteReservaExcedidoException.class, () -> bateria.consumir(999999));

        assertEquals(0, espia.cantidadNotificaciones());
    }

    @Test
    void desuscribirDejaDeNotificarAEseObservador() {
        ObservadorEspia espia = new ObservadorEspia();
        bateria.suscribir(espia);
        bateria.desuscribir(espia);

        bateria.cargar(100);

        assertEquals(0, espia.cantidadNotificaciones());
    }
}

package ar.edu.unahur.obj2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.controlador.ControladorOperacion;
import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;
import ar.edu.unahur.obj2.model.Bateria;
import ar.edu.unahur.obj2.operaciones.OperacionCarga;


class ControladorOperacionesTest {

    private Bateria bateria;
    private ControladorOperacion controlador;

    @BeforeEach
    void setUp() {
        bateria = new Bateria("BAT-01", 1000);
        controlador = new ControladorOperacion();
    }

   

    private void assertEquals(int i, Double nivelEnergia) {
    
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }

   

   

    @Test
    void dosRutinasConNombresDistintosSonIndependientes() throws LimiteReservaExcedidoException {
        controlador.regitrarEnRutina("A", new OperacionCarga(bateria, 100));
        controlador.regitrarEnRutina("B", new OperacionCarga(bateria, 300));

        controlador.ejecutarRutina("A");

        assertEquals(1100, bateria.getNivelEnergia()); // solo se aplicó la rutina A

        controlador.ejecutarRutina("B");

        assertEquals(1400, bateria.getNivelEnergia()); // ahora también B
    }

    
}

package ar.edu.unahur.obj2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.model.Bateria;

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
    // NO ME ANDA EL DEBUGGER AAAAAAAAAAAAAAAAAAAAA
    @Test
    void cargarIncrementaElNivelDeEnergia() {
        bateria.cargar(500);
        assertEquals(1500, bateria.getNivelEnergia());
    }

    // YA ME ANDA ME QUIERO MRIR
    }
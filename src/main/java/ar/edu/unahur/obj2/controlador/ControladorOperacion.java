package ar.edu.unahur.obj2.controlador;

import java.util.HashMap;
import java.util.Map;

import javax.naming.LimitExceededException;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;
import ar.edu.unahur.obj2.operaciones.OperacionEnergia;
import ar.edu.unahur.obj2.operaciones.Rutina;

public class ControladorOperacion {
    // administra las transferencias de energia. solo conoce a la interfaz, creo.
    private Map<String, Rutina> rutinas = new HashMap<>();

    public void ejecuta(OperacionEnergia operacion) throws LimitExceededException, LimiteReservaExcedidoException {
        operacion.ejecutar();
    }

    public void regitrarEnRutina(String nombreRutina, OperacionEnergia operacion) {
        rutinas.computeIfAbsent(nombreRutina, Rutina::new).agregarOperacion(operacion);
    }

    public void ejecutarRutina(String nombreRutina) throws LimiteReservaExcedidoException {
        Rutina rutina = rutinas.get(nombreRutina);
        if(rutina == null) {
            throw new IllegalArgumentException("No existe una rutina registrada con nombre: " + nombreRutina);

        }
        rutina.ejecutar(); // misma interfaz, creo
    }
}

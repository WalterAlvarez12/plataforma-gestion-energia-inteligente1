package ar.edu.unahur.obj2.operaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;

public class Rutina implements OperacionEnergia{

   // implementa la misma interfaz que una operacion individual, creo
    
   private final String nombre;
   private final List<OperacionEnergia> pendientes = new ArrayList<>();
   private final List<OperacionEnergia> ejecutadasConExito = new ArrayList<>();
   
   public Rutina(String nombre) {
    this.nombre = nombre;
   }

   public String getNombre() {
    return nombre;
   }

   public void agregarOperacion(OperacionEnergia operacion) {
    pendientes.add(operacion);
   }

   public List<OperacionEnergia> getOperacionesPendientes() {
    return Collections.unmodifiableList(pendientes);
   }



   
    @Override
    public void ejecutar() throws LimiteReservaExcedidoException {
        
        ejecutadasConExito.clear();
        try {
            for (OperacionEnergia operacion : pendientes) {
                operacion.ejecutar();
                ejecutadasConExito.add(operacion);
            }
        } catch (LimiteReservaExcedidoException e ) {
            deshacerEjecutadasConExito();
            throw e;
        } finally {
            pendientes.clear();
        }
    }

    @Override
    public void deshacer() {
    
        deshacerEjecutadasConExito();
    }

    private void deshacerEjecutadasConExito() {
        for(int i = ejecutadasConExito.size() - 1; i >= 0; i--) {
            ejecutadasConExito.get(i).deshacer();
        }
        ejecutadasConExito.clear();
    }
    
    
}

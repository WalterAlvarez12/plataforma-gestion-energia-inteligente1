package ar.edu.unahur.obj2.operaciones;

import ar.edu.unahur.obj2.excepciones.LimiteReservaExcedidoException;

public interface OperacionEnergia {

    // ejecuta la operacion
    void ejecutar() throws LimiteReservaExcedidoException;


    // deshace el efecto de esta operacion sobre la bateria
    void deshacer();
    
}

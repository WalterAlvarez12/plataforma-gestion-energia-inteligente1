package ar.edu.unahur.obj2.excepciones;

import ar.edu.unahur.obj2.model.Bateria;

public class LimiteReservaExcedidoException extends Exception {

    private final Bateria bateria;
    private final double kwhSolicitados;
    private final double nivelResultante;

    public LimiteReservaExcedidoException(Bateria bateria, double kwhSolicitados, double nivelResultante) {
        super(String.format(
                "La batería '%s' no puede consumir %.2f kWh: el nivel resultante (%.2f kWh) " +
                "supera el límite de reserva permitido (%.2f kWh).",
                bateria.getId(), kwhSolicitados, nivelResultante, Bateria.LimiteDeLaReserva));
        this.bateria = bateria;
        this.kwhSolicitados = kwhSolicitados;
        this.nivelResultante = nivelResultante;
    }

    public Bateria getBateria() { return bateria; }
    public double getKwhSolicitados() { return kwhSolicitados; }
    public double getNivelResultante() { return nivelResultante; }
}
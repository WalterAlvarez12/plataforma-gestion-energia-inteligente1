import java.util.ArrayList;
import java.util.List;

public class Bateria {
    
    // reserva admitida fisica, llega hasta 5000 kw
    public static final Double LimiteDeLaReserva = (double) -5000;

    private String id;
    private Double nivelEnergia;
    private List<ObservadorEnergia> observadores = new ArrayList<>();

    public Bateria(String id, Double nivelEnergiaInicial) {
        this.id = id;
        this.nivelEnergia = nivelEnergiaInicial;
    }
    
    public String getId() {
        return id;
    }

    public Double getNivelEnergia() {
        return nivelEnergia;
    }

    // sube la energia, creo
    public void cargar(Double kwh) {
        nivelEnergia += kwh;
        notificarObservadores(TipoOperacion, carga, kwh);
    }

    // reduce el nivel de energia
    public void consumir(Double kwh) throws LimiteReservaExcedidoException {
        Double nuevoNivel = nivelEnergia - kwh;
        if(nuevoNivel < LimiteDeLaReserva) {
            throw new LimiteReservaEx
        }
    }
}
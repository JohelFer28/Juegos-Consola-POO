package games.batalla;
import games.PuntuacionComparable;

public class PuntuacionBatalla implements PuntuacionComparable {
    private int puntos;
    private int tirosFallados;

    public PuntuacionBatalla(int barcosHundidos, int tirosFallados) {
        this.tirosFallados = tirosFallados;
        // Lógica: 100 puntos por barco hundido, menos 10 por cada tiro fallado
        this.puntos = (barcosHundidos * 100) - (tirosFallados * 10);
    }

    @Override
    public int comparar(PuntuacionComparable otra) {
        return Integer.compare(this.puntos, otra.getValorNumerico());
    }

    @Override
    public int getValorNumerico() { return puntos; }

    @Override
    public String getDesglose() {
        return puntos + " pts (Barcos hundidos x 100 - " + tirosFallados + " fallos x 10)";
    }
}
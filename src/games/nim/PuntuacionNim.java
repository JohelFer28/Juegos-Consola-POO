package games.nim;
import games.PuntuacionComparable;

public class PuntuacionNim implements PuntuacionComparable {
    private int puntos;
    private int turnosSobrevividos;

    public PuntuacionNim(int turnosSobrevividos) {
        this.turnosSobrevividos = turnosSobrevividos;
        // Lógica de puntos: 300 base + 15 por cada turno sobrevivido
        this.puntos = 300 + (turnosSobrevividos * 15);
    }

    @Override
    public int comparar(PuntuacionComparable otra) {
        return Integer.compare(this.puntos, otra.getValorNumerico());
    }

    @Override
    public int getValorNumerico() { return puntos; }

    @Override
    public String getDesglose() {
        return puntos + " pts (300 base + " + turnosSobrevividos + " turnos x 15)";
    }
}
package clases;

import java.util.Objects;

public class Entrada {
        // Atributos
        private Concierto concierto;
        private TipoEntrada tipo;
        // Enumerado para el tipo de entrada
        public enum TipoEntrada {PISTA, GRADA, VIP}
    //Contructores completo y vacio
        public Entrada(Concierto concierto, TipoEntrada tipo) {
            this.concierto = concierto;
            this.tipo = tipo;
        }

        public Entrada() {
        }
        //Getters y Setters
        public Concierto getConcierto() {
            return concierto;
        }

        public void setConcierto(Concierto concierto) {
            this.concierto = concierto;
        }

        public TipoEntrada getTipo() {
            return tipo;
        }

        public void setTipo(TipoEntrada tipo) {
            this.tipo = tipo;
        }
    // Metodo getPrecioTotal()
    public double getPrecioTotal() {
        double precioBase = concierto.getPrecioBase();

        switch (tipo) {
            case GRADA:
                return precioBase;
            case PISTA:
                return precioBase * 1.10; // +10%
            case VIP:
                return precioBase * 1.20; // +20%
            default:
                return precioBase;
        }
    }
    //Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        return Objects.equals(concierto, entrada.concierto) && tipo == entrada.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(concierto, tipo);
    }
    //Representacion del texto
    @Override
    public String toString() {
        return "clases.Entrada de " + getPrecioTotal() + " €";
    }
}

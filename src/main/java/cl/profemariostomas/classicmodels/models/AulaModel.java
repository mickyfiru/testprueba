package cl.profemariostomas.classicmodels.models;

public class AulaModel {

    private Integer aulaId;
    private String nombre;
    private Integer capacidad;

    public AulaModel(Integer aulaId, String nombre, Integer capacidad) {
        this.aulaId = aulaId;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public AulaModel(String nombre, Integer capacidad) {
        this(null, nombre, capacidad);
    }

    public Integer getAulaId() {
        return aulaId;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + capacidad + " personas)";
    }
}

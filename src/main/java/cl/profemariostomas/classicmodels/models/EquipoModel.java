package cl.profemariostomas.classicmodels.models;

public class EquipoModel {

    private Integer equipoId;
    private String descripcion;
    private String numeroSerie;
    private Integer aulaAsignadaId;
    private String aulaNombre;

    public EquipoModel(Integer equipoId, String descripcion, String numeroSerie, Integer aulaAsignadaId, String aulaNombre) {
        this.equipoId = equipoId;
        this.descripcion = descripcion;
        this.numeroSerie = numeroSerie;
        this.aulaAsignadaId = aulaAsignadaId;
        this.aulaNombre = aulaNombre;
    }

    public EquipoModel(String descripcion, String numeroSerie, Integer aulaAsignadaId) {
        this(null, descripcion, numeroSerie, aulaAsignadaId, null);
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public Integer getAulaAsignadaId() {
        return aulaAsignadaId;
    }

    public String getAulaNombre() {
        return aulaNombre;
    }
}

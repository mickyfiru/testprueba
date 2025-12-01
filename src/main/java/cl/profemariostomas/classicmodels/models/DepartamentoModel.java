package cl.profemariostomas.classicmodels.models;

public class DepartamentoModel {

    private int idDepto;
    private String nombreDepto;
    private String centroCosto;

    public DepartamentoModel(int idDepto, String nombreDepto, String centroCosto) {
        this.idDepto = idDepto;
        this.nombreDepto = nombreDepto;
        this.centroCosto = centroCosto;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public String getNombreDepto() {
        return nombreDepto;
    }

    public void setNombreDepto(String nombreDepto) {
        this.nombreDepto = nombreDepto;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    @Override
    public String toString() {
        return this.nombreDepto + " (#" + this.idDepto + ")";
    }
}

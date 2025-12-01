package cl.profemariostomas.classicmodels.models;

public class TareaModel {

    private int idTarea;
    private int idEmpleadoAsignado;
    private String estado;
    private int horasEstimadas;

    public TareaModel(int idTarea, int idEmpleadoAsignado, String estado, int horasEstimadas) {
        this.idTarea = idTarea;
        this.idEmpleadoAsignado = idEmpleadoAsignado;
        this.estado = estado;
        this.horasEstimadas = horasEstimadas;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdEmpleadoAsignado() {
        return idEmpleadoAsignado;
    }

    public void setIdEmpleadoAsignado(int idEmpleadoAsignado) {
        this.idEmpleadoAsignado = idEmpleadoAsignado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(int horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }
}

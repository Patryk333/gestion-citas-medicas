package com.consultorio.model;

public class CitaMedica {
    private int id;
    private String pacienteNombre;
    private String medicoNombre;
    private String fecha;
    private int pacienteId;  // Añadir estos atributos
    private int medicoId;

    // Constructor
    public CitaMedica(int id, String pacienteNombre, String medicoNombre, String fecha, int pacienteId, int medicoId) {
        this.id = id;
        this.pacienteNombre = pacienteNombre;
        this.medicoNombre = medicoNombre;
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getMedicoNombre() {
        return medicoNombre;
    }

    public void setMedicoNombre(String medicoNombre) {
        this.medicoNombre = medicoNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }
}

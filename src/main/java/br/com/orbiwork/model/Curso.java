package br.com.orbiwork.model;

public class Curso {

    private Long id;
    private String titulo;
    private int cargaHoraria;
    private Long trilhaId;

    public Curso() {}

    public Curso(Long id, String titulo, int cargaHoraria, Long trilhaId) {
        this.id = id;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.trilhaId = trilhaId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public Long getTrilhaId() { return trilhaId; }
    public void setTrilhaId(Long trilhaId) { this.trilhaId = trilhaId; }
}

package model;

public class Bairros {
    private int id;
    private String titulo;
    private String descricao;
    private String statusBairros;
    private Usuario usuario;

    public Bairros(String titulo, String descricao, String statusBairros, Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.statusBairros = statusBairros;
        this.usuario = usuario;
    }

    public Bairros(int id, String titulo, String descricao, String statusBairros) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.statusBairros = statusBairros;
    }
      public Bairros (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatusBairros() {
        return statusBairros;
    }

    public void setStatusBairros(String statusBairros) {
        this.statusBairros = statusBairros;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Bairros{" + "id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", statusBairros=" + statusBairros + ", usuario=" + usuario + '}';
    }
    
      
}

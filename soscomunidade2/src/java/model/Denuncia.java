package model;

public class Denuncia{
    private int id;
    private String denuncia;
    private String descricao;
    private String statusDenuncia;
    private Usuario usuario;

    public Denuncia(String denuncia, String descricao, String statusDenuncia, Usuario usuario) {
        this.denuncia = denuncia;
        this.descricao = descricao;
        this.statusDenuncia = statusDenuncia;
        this.usuario = usuario;
    }

    public Denuncia(int id, String denuncia, String descricao, String statusDenuncia) {
        this.id = id;
        this.denuncia = denuncia;
        this.descricao = descricao;
        this.statusDenuncia = statusDenuncia;
    }
      public Denuncia (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(String denuncia) {
        this.denuncia = denuncia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatusDenuncia() {
        return statusDenuncia;
    }

    public void setStatusDenuncia(String statusDenuncia) {
        this.statusDenuncia = statusDenuncia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "denuncia{" + "id=" + id + ", denuncia=" + denuncia + ", descricao=" + descricao + ", statusDenuncia=" + statusDenuncia + ", usuario=" + usuario + '}';
    }    
      
}

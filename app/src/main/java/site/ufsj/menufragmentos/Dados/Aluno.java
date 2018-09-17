package site.ufsj.menufragmentos.Dados;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Aluno extends RealmObject {
    private String nome;
    @PrimaryKey
    private String matricula;
    private byte [] foto = null;
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }



    public Aluno(){};

    public Aluno(String nome, String matricula){
        this.nome = nome;
        this.matricula = matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}

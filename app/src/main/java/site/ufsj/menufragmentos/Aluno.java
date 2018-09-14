package site.ufsj.menufragmentos;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Aluno extends RealmObject {
    private String nome;
    @PrimaryKey
    private String matricula;

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

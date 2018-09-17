package site.ufsj.menufragmentos.Dados;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Aula extends RealmObject {
    private Date data;
    private RealmList<Presenca> alunos;
    private String conteudo;

    public RealmList<Presenca> getAlunos() {
        return alunos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}

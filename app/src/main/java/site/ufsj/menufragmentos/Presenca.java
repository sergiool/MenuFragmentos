package site.ufsj.menufragmentos;

import io.realm.RealmObject;

public class Presenca extends RealmObject {
    private Aluno aluno;
    private boolean presente;

    public Presenca() {
    }

    public Presenca(Aluno aluno, boolean presente) {
        this.aluno = aluno;
        this.presente = presente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}

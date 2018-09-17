package site.ufsj.menufragmentos.Dados;

import io.realm.Realm;
import io.realm.RealmResults;

public class VetorAluno {

    public static RealmResults<Aluno> alunos;

    public static void update(){

        Realm realm = Realm.getDefaultInstance();
        //alunos = new ArrayList<Aluno>();
        alunos = realm.where(Aluno.class).findAll();
    }

}

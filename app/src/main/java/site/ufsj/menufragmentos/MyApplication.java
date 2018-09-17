package site.ufsj.menufragmentos;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import site.ufsj.menufragmentos.Dados.VetorAluno;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        VetorAluno.update();
    }
}
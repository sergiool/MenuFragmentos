package site.ufsj.menufragmentos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;
import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

          // Fragmento Padrão
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.const_lay, new NovaAula()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_fragment1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.const_lay, new ChamadaFragment()).commit();
                return true;
            case R.id.action_fragment2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.const_lay, new NovaAula()).commit();
                return true;
            case R.id.action_fragment3:
                InputStream inputStream = getResources().openRawResource(R.raw.alunos);
                CSVFile csvFile = new CSVFile(inputStream);
                ArrayList<String[]> myList = (ArrayList<String[]>) csvFile.read();
                realm.beginTransaction();
                for (int i=0; i<myList.size();i++){
                    realm.copyToRealm(new Aluno(myList.get(i)[1], myList.get(i)[0]));
                    //    alunos.add(new Aluno(myList.get(i)., myList.get(i)[0]));
                }
                realm.commitTransaction();
                VetorAluno.update();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

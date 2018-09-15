package site.ufsj.menufragmentos;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCorrecoes extends Fragment {

    AlunoAdapter alunoAdapter;
    public FragmentCorrecoes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<Aula> aulas = realm.where(Aula.class).findAll().sort("data");
        final CheckBox check = v.findViewById(R.id.checkBox);
        String [] atividades = new String[aulas.size()];

        for (int i=0; i<aulas.size();i++){
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));
            atividades[i] = sdf.format(aulas.get(i).getData());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, atividades);

        final Spinner sp = v.findViewById(R.id.spinner);
        sp.setAdapter(adapter);
        alunoAdapter = new AlunoAdapter(getActivity(), aulas.get(sp.getSelectedItemPosition()));
        final Spinner sp2 = v.findViewById(R.id.spinner2);
        sp2.setAdapter(alunoAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                alunoAdapter.setAula(aulas.get(position));
                check.setChecked(aulas.get(position).getAlunos().get(sp2.getSelectedItemPosition()).isPresente());
                if (check.isChecked())
                    sp2.getSelectedView().setBackgroundColor(Color.GREEN);
                else
                    sp2.getSelectedView().setBackgroundColor(Color.RED);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                check.setChecked(aulas.get(sp.getSelectedItemPosition()).getAlunos().get(position).isPresente());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        check.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                aulas.get(sp.getSelectedItemPosition()).getAlunos().get(sp2.getSelectedItemPosition()).setPresente(check.isChecked());
                realm.commitTransaction();
                if (check.isChecked())
                    sp2.getSelectedView().setBackgroundColor(Color.GREEN);
                else
                    sp2.getSelectedView().setBackgroundColor(Color.RED);
            }
        });

        check.setChecked(aulas.get(sp.getSelectedItemPosition()).getAlunos().get(sp.getSelectedItemPosition()).isPresente());

        return v;
    }



}

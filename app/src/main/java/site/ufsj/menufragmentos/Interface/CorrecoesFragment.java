package site.ufsj.menufragmentos.Interface;


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
import site.ufsj.menufragmentos.Adapters.AlunoAdapterSpinner;
import site.ufsj.menufragmentos.Dados.Aula;
import site.ufsj.menufragmentos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CorrecoesFragment extends Fragment {

    AlunoAdapterSpinner alunoAdapter;
    public CorrecoesFragment() {
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
        alunoAdapter = new AlunoAdapterSpinner(getActivity(), aulas.get(sp.getSelectedItemPosition()));
        final Spinner sp2 = v.findViewById(R.id.spinner2);
        sp2.setAdapter(alunoAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                alunoAdapter.setAula(aulas.get(position));
                System.out.println("SP1: " + position + "SP2: "+sp2.getSelectedItemPosition());
                if (position < aulas.size() && sp2.getSelectedItemPosition() < aulas.get(position).getAlunos().size()) {
                    check.setChecked(aulas.get(position).getAlunos().get(sp2.getSelectedItemPosition()).isPresente());
                }
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
                if (sp.getSelectedItemPosition() < aulas.size() && position  < aulas.get(position).getAlunos().size())
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
            }
        });

//        check.setChecked(aulas.get(sp.getSelectedItemPosition()).getAlunos().get(sp2.getSelectedItemPosition()).isPresente());
        System.out.println("SP1: " + sp.getSelectedItemPosition() + "SP2: "+sp2.getSelectedItemPosition());
        return v;
    }



}

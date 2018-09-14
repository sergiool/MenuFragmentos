package site.ufsj.menufragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCorrecoes extends Fragment {


    public FragmentCorrecoes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Aula> aulas = realm.where(Aula.class).findAll();

        String [] atividades = new String[aulas.size()];

        for (int i=0; i<aulas.size();i++){
            atividades[i] = aulas.get(i).getData().toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, atividades);

        Spinner sp = v.findViewById(R.id.spinner);
        sp.setAdapter(adapter);

        Spinner sp2 = v.findViewById(R.id.spinner2);
        sp2.setAdapter(new AlunoAdapter(getActivity(), aulas.get(sp.getSelectedItemPosition())));
        return v;
    }

}

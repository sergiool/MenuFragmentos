package site.ufsj.menufragmentos;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChamadaFragment extends Fragment {

    private Realm realm;

    public ChamadaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_blank, container, false);
        realm = Realm.getDefaultInstance();
        ListView lv = (ListView) v.findViewById(R.id.lv);
        lv.setAdapter(new AlunoAdapter(getActivity(), NovaAula.ultimaAula));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> adapter, View view, int position, long arg){
                // TODO Auto-generated method stub
                if (NovaAula.ultimaAula != null) {
                    boolean presente = NovaAula.ultimaAula.getAlunos().get(position).isPresente();
                    realm.beginTransaction();
                    NovaAula.ultimaAula.getAlunos().get(position).setPresente(!presente);
                    realm.commitTransaction();
                    if (presente)
                        view.setBackgroundColor(Color.RED);
                    else
                        view.setBackgroundColor(Color.GREEN);
                }
            }
        });
        return v;
    }
}

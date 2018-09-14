package site.ufsj.menufragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {
    final Realm realm;
    EditText matricula, nome;
    Spinner sp;

    public BlankFragment2() {
        // Required empty public constructor
        realm = Realm.getDefaultInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        nome = v.findViewById(R.id.editText);
        matricula = v.findViewById(R.id.editText2);
        sp = (Spinner) v.findViewById(R.id.sp);
        sp.setAdapter(new AlunoAdapter(getActivity(), NovaAula.ultimaAula));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                nome.setText(VetorAluno.alunos.get(position).getNome());
                matricula.setText(VetorAluno.alunos.get(position).getMatricula());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        Button btnSend = (Button) v.findViewById(R.id.button);

        btnSend .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                VetorAluno.alunos.get(sp.getSelectedItemPosition()).setNome(nome.getText().toString());
//                VetorAluno.alunos.get(sp.getSelectedItemPosition()).setMatricula(matricula.getText().toString());
                realm.commitTransaction();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.const_lay, new FragmentCorrecoes()).commit();
            }
        });

        return v;


    }
}

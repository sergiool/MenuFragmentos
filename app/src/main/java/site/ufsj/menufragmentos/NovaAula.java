package site.ufsj.menufragmentos;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class NovaAula extends Fragment {

    private Realm realm;

    public static Aula ultimaAula;

    public NovaAula() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nova_aula, container, false);
        realm = Realm.getDefaultInstance();

        final Calendar myCalendar = Calendar.getInstance();
        final EditText editdata = v.findViewById(R.id.editData);
        final EditText editcont = v.findViewById(R.id.editConteudo);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            private void updateLabel() {

                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

                editdata.setText(sdf.format(myCalendar.getTime()));
            }

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editdata.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        Button b = v.findViewById(R.id.buttonaula);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Prepara a data
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date convertedDate = new Date();
                try {
                    convertedDate = dateFormat.parse(editdata.getText().toString());
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                realm.beginTransaction();
                Aula aula = realm.createObject(Aula.class);
                aula.setData(convertedDate);
                aula.setConteudo(editcont.getText().toString());
                for (int i=0; i<VetorAluno.alunos.size();i++){
                    Presenca p =  realm.createObject(Presenca.class);
                    p.setAluno(VetorAluno.alunos.get(i));
                    p.setPresente(false);
                    aula.getAlunos().add(p);
                }
                realm.commitTransaction();
                ultimaAula = aula;
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.const_lay, new ChamadaFragment()).commit();

            }
        });

        return v;
    }

    }

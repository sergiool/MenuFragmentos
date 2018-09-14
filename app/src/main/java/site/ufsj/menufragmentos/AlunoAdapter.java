package site.ufsj.menufragmentos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;

public class AlunoAdapter extends BaseAdapter {
    private Context context;
    private Aula aula;

    public AlunoAdapter(Context context, Aula aula){
        this.context = context;
        this.aula = aula;
    }

    @Override
    public int getCount() {
        return VetorAluno.alunos.size();
    }

    @Override
    public Object getItem(int position)
    {
        return VetorAluno.alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Aluno aluno = VetorAluno.alunos.get(position);
        View layout;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.aluno, null);
        }
        else{
            layout = convertView;
        }


        TextView nome = (TextView) layout.findViewById(R.id.t1);
        nome.setText(aluno.getNome());

        TextView matricula = (TextView) layout.findViewById(R.id.t2);
        matricula.setText(aluno.getMatricula());

        if (aula != null) {
            boolean presente = aula.getAlunos().get(position).isPresente();
            if (!presente)
                layout.setBackgroundColor(Color.RED);
            else
                layout.setBackgroundColor(Color.GREEN);
        }
        return layout;
    }

}

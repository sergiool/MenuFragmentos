package site.ufsj.menufragmentos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlunoAdapter extends BaseAdapter {
    private Context context;

    public AlunoAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return VetorAluno.alunos.size();
    }

    @Override
    public Object getItem(int position) {
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

        if(position % 2 == 0){
            layout.setBackgroundColor(Color.YELLOW);
        }

        return layout;
    }

}
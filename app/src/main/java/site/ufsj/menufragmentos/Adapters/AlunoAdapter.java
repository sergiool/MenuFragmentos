package site.ufsj.menufragmentos.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import io.realm.Realm;
import site.ufsj.menufragmentos.Dados.Aluno;
import site.ufsj.menufragmentos.Dados.Aula;
import site.ufsj.menufragmentos.R;
import site.ufsj.menufragmentos.Dados.VetorAluno;

public class AlunoAdapter extends BaseAdapter {
    private Context context;
    private Aula aula;
    private Realm realm;

    public AlunoAdapter(Context context, Aula aula){
        this.context = context;
        this.aula = aula;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public int getCount() {
        return VetorAluno.alunos.size();
    }

    public void setAula(Aula aula) {
        this.aula = aula;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

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

        ImageView iv = layout.findViewById(R.id.iv);

        if (aluno.getFoto() != null){
            iv.setImageBitmap(BitmapFactory.decodeByteArray(aluno.getFoto(), 0,aluno.getFoto().length ));
        } else {
             iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_background));
        }

        final Switch sw = layout.findViewById(R.id.switch1);

        if (aula != null) {
            if (position < aula.getAlunos().size()) {
                sw.setChecked(aula.getAlunos().get(position).isPresente());
                sw.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        realm.beginTransaction();
                        aula.getAlunos().get(position).setPresente(sw.isChecked());
                        realm.commitTransaction();
                    }
                });
            }
        }

        return layout;
    }

}

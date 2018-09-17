package site.ufsj.menufragmentos.Interface;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;
import site.ufsj.menufragmentos.Adapters.AlunoAdapterSpinner;
import site.ufsj.menufragmentos.R;
import site.ufsj.menufragmentos.Dados.VetorAluno;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FotoFragment extends Fragment {

    private static final int TIRAR_FOTO = 64857;
    ImageView iv;
    Spinner sp2;

    public FotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_foto, container, false);

        Button btn = v.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressLint("SimpleDateFormat")
            public void onClick(View view) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, TIRAR_FOTO);
            }
        });

        sp2 = v.findViewById(R.id.spinnerFoto);
        sp2.setAdapter(new AlunoAdapterSpinner(getActivity(), null));

        iv = v.findViewById(R.id.imageView2);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TIRAR_FOTO) {
            if (resultCode == RESULT_OK) {
                if(data != null) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    iv.setImageBitmap(bitmap);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    boolean validaCompressao = bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    byte[] fotoBinario = outputStream.toByteArray();
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    VetorAluno.alunos.get(sp2.getSelectedItemPosition()).setFoto(fotoBinario);
                    realm.commitTransaction();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getActivity(), "A captura foi cancelada",
                            Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getActivity(), "A câmera foi fechada",
                            Toast.LENGTH_SHORT);
                }
            }
        }
    }
}

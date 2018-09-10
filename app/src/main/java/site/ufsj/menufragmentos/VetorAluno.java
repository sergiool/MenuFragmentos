package site.ufsj.menufragmentos;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VetorAluno {

    public static ArrayList<Aluno> alunos;
    public VetorAluno(InputStream inputStream){
        alunos = new ArrayList<Aluno>();
        CSVFile csvFile = new CSVFile(inputStream);
        ArrayList<String[]> myList = (ArrayList<String[]>) csvFile.read();
        for (int i=0; i<myList.size();i++){
            alunos.add(new Aluno(myList.get(i)[1], myList.get(i)[0]));
        }
    }

}

package Repository;

import Model.Student;

import java.util.List;

public interface DataMaster {

    public void putData(List<Student> students);

    public List<Student> getData();

}

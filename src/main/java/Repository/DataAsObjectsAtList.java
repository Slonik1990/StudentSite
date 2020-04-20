package Repository;

import Model.Student;

import java.util.ArrayList;
import java.util.List;

public class DataAsObjectsAtList implements DataMaster {

    private List<Student> data = new ArrayList<>();

    @Override
    public void putData(List<Student> students) {
        data = students;
    }

    @Override
    public List<Student> getData() {
        return data;
    }
}

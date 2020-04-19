package repository;

import model.Student;
import repository.DataMaster;

import java.util.ArrayList;
import java.util.List;

public class DataList implements DataMaster {

    private static List<Student> data = new ArrayList<>();

    @Override
    public void putData(List<Student> students) {
        data = students;
    }

    @Override
    public List<Student> getData() {
        return data;
    }
}

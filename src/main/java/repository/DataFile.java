package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;
import repository.DataMaster;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class DataFile implements DataMaster {

    private String path = System.getProperty("user.home") + File.separator  +"students.json";
    @Override
    public void putData(List<Student> students) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        try (FileWriter fw = new FileWriter(file, false)) {
            for (Student toRecord : students) {
                fw.write(objectMapper.writeValueAsString(toRecord) + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("файл не найден!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Student> getData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> toReturn = new LinkedList<>();
        File file = new File(path);
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            while (scanner.hasNextLine()) {
                String json = scanner.nextLine();
                Student fromFile = objectMapper.readValue(json, Student.class);
                toReturn.add(fromFile);
            }
        } catch (FileNotFoundException e) {
            System.out.println("файл не найден!!!!");
            return toReturn;//возвращается пустая коллекция если файла не существует
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}

package Controller;

import Repository.DataAsJSONAtFile;
import Repository.DataAsObjectsAtList;
import Repository.DataMaster;
import Service.StudentService;
import Service.StudentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.ls.LSOutput;

@Configuration
public class AppConfig {

    @Bean
    public DataMaster dataMaster() {
        System.out.println("создался репозиторий");
        return new DataAsJSONAtFile();
    }

    @Bean
    public StudentService workerService(DataMaster dataMaster) {
        System.out.println("создался сервис");
        return new StudentServiceImpl(dataMaster) {
        };
    }


}


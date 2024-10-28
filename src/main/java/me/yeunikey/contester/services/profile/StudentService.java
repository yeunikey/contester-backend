package me.yeunikey.contester.services.profile;

import com.google.gson.JsonObject;
import me.yeunikey.contester.entities.profile.Student;
import me.yeunikey.contester.repositories.profile.StudentRepository;
import me.yeunikey.contester.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<Student, String, StudentRepository> {

    public JsonObject asJson(Student student) {

        JsonObject studentJson = new JsonObject();

        studentJson.addProperty("uniqueId", student.getUniqueId());
        studentJson.addProperty("name", student.getName());
        studentJson.addProperty("surname", student.getSurname());
        studentJson.addProperty("group", student.getGroup() == null ? null : student.getGroup().getGroupId());
        studentJson.addProperty("teacherId", student.getTeacher() == null ? null : student.getTeacher().getUniqueId());

        return studentJson;
    }

}

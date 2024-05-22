package se.lexicon.data_acces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentDaoListImpl implements StudentDao{
    //Create a list for database storage
    private List<Student> students;

    //Dependency injection
    public StudentDaoListImpl() {
        this.students = new ArrayList<>();
    }

    @Override
    public Student save(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public Student findId(int id) {
        return students.stream().
                filter(student -> student.getId() == id).
                findFirst().
                orElseThrow(() -> new IllegalArgumentException("Student not found"));

    }

    @Override
    public List<Student> findAll() {

        return new ArrayList<>(students);
    }

    @Override
    public void delete(int id) {
        Optional<Student> optionalStudent = students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();

        optionalStudent.ifPresent(students::remove);

    }
}

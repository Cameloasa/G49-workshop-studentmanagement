package se.lexicon.data_acces;

import se.lexicon.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Student> optionalStudent = students.stream().
                filter(student -> student.getId() == id).
                findFirst();
       return optionalStudent.orElse(null);
    }

    @Override
    public List<Student> findAll() {


        return students;
    }

    @Override
    public void delete(int id) {
        Student deleteStudent = findId(id);
        students.remove(deleteStudent);

    }
}

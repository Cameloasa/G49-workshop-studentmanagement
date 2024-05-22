package se.lexicon.data_acces;

import se.lexicon.models.Student;

import java.util.List;

public interface StudentDao {
    Student save(Student student);

    Student findId(int id);

    List<Student> findAll();

    void delete(int id);
}

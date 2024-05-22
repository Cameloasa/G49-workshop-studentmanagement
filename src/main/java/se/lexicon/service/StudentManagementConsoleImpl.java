package se.lexicon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.data_acces.StudentDao;
import se.lexicon.models.Student;
import se.lexicon.util.UserInputService;

import java.util.List;
import java.util.Optional;

@Component
public class StudentManagementConsoleImpl implements StudentManagement {

    //Fields
    private UserInputService scannerService;
    private StudentDao studentDao;

    //Constructor

    @Autowired
    public StudentManagementConsoleImpl(UserInputService scannerService, StudentDao studentDao) {
        this.scannerService = scannerService;
        this.studentDao = studentDao;
    }

    @Override
    public Student create() {
        // Prompt the user for the student's name
        System.out.print("Enter the student's name: ");
        String name = scannerService.getString();

        // Prompt the user for the student's age
        System.out.print("Enter the student's id: ");
        int age = scannerService.getInt();

        // Create a new Student object with the provided name and age
        Student student = new Student();

        // Save the student to the database using the StudentDao
        studentDao.save(student);

        // Return the created student
        return student;

    }

    @Override
    public Student save(Student student) {
        if(studentDao == null)
            throw new IllegalArgumentException("StudentDao is null");
        Optional.ofNullable(student).ifPresent(studentDao::save);
        return student;
    }

    @Override
    public Student find(int id) {
       Optional<Student> optionalStudent = Optional.ofNullable(studentDao.findId(id));
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
       throw new IllegalArgumentException("Student not found");
    }

    @Override
    public Student remove(int id) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentDao.findId(id));
        if (optionalStudent.isPresent()) {
           studentDao.delete(id);
        }
        throw new IllegalArgumentException("Student not found");
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student edit(Student student) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentDao.findId(student.getId()));
        if (!optionalStudent.isPresent()) throw new IllegalArgumentException("Student not found");
        if (student.getName() != null) optionalStudent.get().setName(student.getName());

        return studentDao.save(optionalStudent.get());

    }
}

package se.lexicon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.data_acces.StudentDao;
import se.lexicon.models.Student;
import se.lexicon.util.UserInputService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

        // Prompt the user for the student's id
        System.out.print("Enter the student's id: ");

        int id = scannerService.getInt();
        // Prompt the user for the student's name
        System.out.print("Enter the student's name: ");
        String name = scannerService.getString();

        // Create a new Student object with the provided id and name
        Student student = new Student(id, name);
        student.setName(name);

        // Save the student to the database using the StudentDao and return the result
       return studentDao.save(student);

    }

    @Override
    public Student save(Student student) {
        if(studentDao == null)
            throw new IllegalArgumentException("StudentDao is null");

        return studentDao.save(student);
    }

    @Override
    public Student find(int id) {
        Student student = studentDao.findId(id);
        if(student == null) throw new IllegalArgumentException("Student not found");
        return student;

    }

    @Override
    public Student remove(int id) {
        //todo, save to a new list
       Student student = studentDao.findId(id);
        if(student != null) {
            studentDao.delete(id);
            return student;
        }
        throw new IllegalArgumentException("Student not found");
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student edit(Student student) {
        //todo - look again at this method
        Student existingStudent = studentDao.findId(student.getId());
        if (existingStudent == null) {
            throw new IllegalArgumentException("Student not found");
        }
        // Update the student's name if it's not null using scanner
        System.out.println("Enter the student's name: ");

        String updatedName = scannerService.getString();


        // If the user provided a name, update the existing student's name
        if (!updatedName.isEmpty()) {
            existingStudent.setName(updatedName);
        }


        return studentDao.save(existingStudent);
    }
}

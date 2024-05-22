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
        //Check if the student exists
        if(studentDao.findId(id) == null) throw new IllegalArgumentException("Student not found");
        //If the student exists, return the student
        return studentDao.findId(id);

    }

    @Override
    public Student remove(int id) {
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
        Student existingStudent = studentDao.findId(student.getId());
        if (existingStudent == null) {
            throw new IllegalArgumentException("Student not found");
        }
        // Update the student's name if it's not null using scanner
        System.out.println("Enter the student's name: ");

        String name = scannerService.getString();

        // If the student's name is null, do nothing


        if (student.getName() != null) {
            existingStudent.setName(student.getName());
        }

        return studentDao.save(existingStudent);
    }
}

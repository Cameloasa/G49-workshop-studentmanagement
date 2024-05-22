package se.lexicon.models;

public class Student {
    //Fields
     private int id;
     private String name;

     //Default constructor
     public Student(){

     }

    //Constructor to fetch data from database
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    // Constructor
    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

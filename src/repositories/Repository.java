package repositories;

import java.util.ArrayList;
import java.util.List;
import models.Student ;
import models.Teacher ;
import models.AdministrativeStaff ;

// Will keep track of student, teacher, course, and administrative staff objects

public class Repository<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);

        if ( item instanceof Student){
            System.out.println("Student: " + ((Student) item).getStudentID() + "added to the repository");
        }

        if ( item instanceof Teacher){
            System.out.println("Teacher: " + ((Teacher) item).getTeacherID() + " " + "added to the repository");
        }

        if ( item instanceof AdministrativeStaff){
            System.out.println("Administrative Staff: " + ((AdministrativeStaff) item).getStaffID() + " " + "added to the repository");
        }

    }

    public void remove(T item) {
        items.remove(item);

        if ( item instanceof Student){
            System.out.println("Student: " + ((Student) item).getStudentID() + "removed from the repository");
        }

        if ( item instanceof Teacher){
            System.out.println("Teacher: " + ((Teacher) item).getTeacherID() + "removed from the repository");
        }

        if ( item instanceof AdministrativeStaff){
            System.out.println("Administrative Staff: " + ((AdministrativeStaff) item).getStaffID() + "removed from the repository");
        }
    }


    public List<T> getAll() {
        return items;
    }
}


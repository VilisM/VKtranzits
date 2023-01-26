package lv.vktranzits.demo.services;

import java.util.ArrayList;

import lv.vktranzits.demo.models.*;

public interface IEmployeeCourseService {

    ArrayList<EmployeeCourse> selectAllEmployeeResults();

    ArrayList<EmployeeCourse> selectEmployeeResultsByEmployeeId(int employeeId);

    ArrayList<String> selectAllEmployeeCourses(int employeeId);

    ArrayList<EmployeeCourse> selectEmployeeSpecificCourse(String title, int employeeId);

    double getEmployeeSpecificCourseAverageGrade(String title, int employeeId);

}

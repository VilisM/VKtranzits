package lv.vktranzits.demo.services;

import java.util.ArrayList;

import lv.vktranzits.demo.models.*;

public interface IEmployeeCourseService {

    public abstract ArrayList<EmployeeCourse> selectAllEmployeeResults();

    public abstract ArrayList<EmployeeCourse> selectEmployeeResultsByEmployeeId(int employeeId);

}

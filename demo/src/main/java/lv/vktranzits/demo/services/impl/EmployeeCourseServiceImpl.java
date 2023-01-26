package lv.vktranzits.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.models.EmployeeCourse;
import lv.vktranzits.demo.repos.IEmployeeCourseRepo;
import lv.vktranzits.demo.services.IEmployeeCourseService;

@Service
public class EmployeeCourseServiceImpl implements IEmployeeCourseService {

    @Autowired
    private IEmployeeCourseRepo empCourseRepo;

    @Override
    public ArrayList<EmployeeCourse> selectAllEmployeeResults() {
        return (ArrayList<EmployeeCourse>) empCourseRepo.findAll();
    }

    @Override
    public ArrayList<EmployeeCourse> selectEmployeeResultsByEmployeeId(int employeeId) {
        return (ArrayList<EmployeeCourse>) empCourseRepo.findTop5ByEmployeeIdEmOrderByDateDesc(employeeId);
    }

    @Override
    public ArrayList<String> selectAllEmployeeCourses(int employeeId) {
        return (ArrayList<String>) empCourseRepo.findDistinctTitleByEmployeeIdEm(employeeId);
    }

    @Override
    public ArrayList<EmployeeCourse> selectEmployeeSpecificCourse(String title, int employeeId) {
        return (ArrayList<EmployeeCourse>) empCourseRepo.findAllByTitleAndEmployeeIdEmOrderByDateDesc(title, employeeId);
    }

    @Override
    public double getEmployeeSpecificCourseAverageGrade(String title, int employeeId) {
        return empCourseRepo.getAverageGradeByTitleAndEmployeeId(title, employeeId);
    }
    
}

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
    
}

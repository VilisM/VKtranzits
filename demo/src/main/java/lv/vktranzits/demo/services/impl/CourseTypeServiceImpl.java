package lv.vktranzits.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.models.CourseType;
import lv.vktranzits.demo.repos.ICourseTypeRepo;
import lv.vktranzits.demo.services.ICourseTypeService;

@Service
public class CourseTypeServiceImpl implements ICourseTypeService {
    
    @Autowired
    private ICourseTypeRepo coTypeRepo;

    @Override
    public ArrayList<CourseType> selectAllCourseTypes() {
        return (ArrayList<CourseType>) coTypeRepo.findAll();
    }
}

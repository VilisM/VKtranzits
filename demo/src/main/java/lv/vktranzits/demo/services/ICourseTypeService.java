package lv.vktranzits.demo.services;

import java.util.ArrayList;

import lv.vktranzits.demo.models.CourseType;

public interface ICourseTypeService {

    public abstract ArrayList<CourseType> selectAllCourseTypes();
    
}

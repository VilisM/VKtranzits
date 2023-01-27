package lv.vktranzits.demo.services;

import java.util.ArrayList;

import lv.vktranzits.demo.models.CourseType;

public interface ICourseTypeService {

    ArrayList<CourseType> selectAllCourseTypes();
}

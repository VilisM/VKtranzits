package lv.vktranzits.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.vktranzits.demo.models.CourseType;
import lv.vktranzits.demo.models.Employee;

public interface ICourseTypeService {

    public abstract ArrayList<CourseType> selectAllCourseTypes();
}

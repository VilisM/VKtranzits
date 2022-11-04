package lv.vktranzits.demo.services;

import java.util.ArrayList;

import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.models.Position;

public interface IPositionService {

    public abstract ArrayList<Position> selectAllPositions();
    
    public abstract ArrayList<Position> selectAllEmployeePositions(int employeeId);
    
}

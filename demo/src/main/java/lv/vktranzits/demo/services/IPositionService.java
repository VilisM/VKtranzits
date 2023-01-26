package lv.vktranzits.demo.services;

import java.util.ArrayList;

import lv.vktranzits.demo.models.Position;

public interface IPositionService {

    ArrayList<Position> selectAllPositions();
    
    ArrayList<Position> selectAllEmployeePositions(int employeeId);
    
}

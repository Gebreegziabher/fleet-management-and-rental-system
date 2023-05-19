package miu.edu.carfleet.dto;

import java.util.ArrayList;
import java.util.List;

public class CarsDto {
    public List<CarDto> cars = new ArrayList<>();
    public CarsDto(List<CarDto> cars){
        this.cars = cars;
    }
    public List<CarDto> getCars(){
        return cars;
    }
}

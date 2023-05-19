package client.dto;

import java.util.List;

public class CarsDto {
    public List<CarDto> cars;
    public CarsDto(){}
    public CarsDto(List<CarDto> cars){
        this.cars = cars;
    }
    public List<CarDto> getCars(){
        return cars;
    }
}

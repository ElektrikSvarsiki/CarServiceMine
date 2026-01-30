package com.abbtech.service;



import com.abbtech.exception.CarErrorEnum;
import com.abbtech.exception.CarException;
import com.abbtech.model.Car;
import com.abbtech.model.CarDetails;
import com.abbtech.repository.CarRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional(readOnly = true)
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Car getCarById(Integer id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));
    }

    @Transactional
    public Car createCar(Car car) {
        CarDetails details = car.getCarDetails();
        if (details != null) {
            details.setCar(car);
        }
        return carRepository.save(car);
    }

    @Transactional
    public Car updateCar(Integer id, Car updatedCar) {
        Car existing = getCarById(id);
        existing.setModel(updatedCar.getModel());
        existing.setVin(updatedCar.getVin());
        existing.setRegistrationNumber(updatedCar.getRegistrationNumber());
        existing.setMileageKm(updatedCar.getMileageKm());
        existing.setProductionYear(updatedCar.getProductionYear());
        existing.setFeatures(updatedCar.getFeatures());

        CarDetails updatedDetails = updatedCar.getCarDetails();
        if (updatedDetails != null) {
            updatedDetails.setCar(existing);
            existing.setCarDetails(updatedDetails);
        }

        return carRepository.save(existing);
    }

    @Transactional
    public void deleteCar(Integer id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }
}

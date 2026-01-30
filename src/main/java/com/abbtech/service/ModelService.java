package com.abbtech.service;

import com.abbtech.exception.CarErrorEnum;
import com.abbtech.exception.CarException;
import com.abbtech.model.Model;
import com.abbtech.repository.ModelRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;

    @Transactional(readOnly = true)
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Model getModelById(Integer id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new CarException(CarErrorEnum.MODEL_NOT_FOUND));
    }

    @Transactional
    public Model createModel(Model model) {
        return modelRepository.save(model);
    }

    @Transactional
    public Model updateModel(Integer id, Model updatedModel) {
        Model existing = getModelById(id);
        existing.setName(updatedModel.getName());
        existing.setCategory(updatedModel.getCategory());
        existing.setYearFrom(updatedModel.getYearFrom());
        existing.setYearTo(updatedModel.getYearTo());
        existing.setBrand(updatedModel.getBrand());
        return modelRepository.save(existing);
    }

    @Transactional
    public void deleteModel(Integer id) {
        Model model = getModelById(id);
        modelRepository.delete(model);
    }
}

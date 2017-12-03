package com.dpiotr.services;

import com.dpiotr.model.Subject;
import com.dpiotr.model.viewmodels.SubjectViewModel;
import com.dpiotr.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dpiotr on 01.12.17.
 */

@Component
public class SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void addSubject(SubjectViewModel subjectViewModel) {
        Subject subjectToSave = new Subject(subjectViewModel.getName(), subjectViewModel.getDescription());
        subjectRepository.save(subjectToSave);
    }

    public void editSubject(Subject subjectToUpdate, SubjectViewModel subjectViewModel) {
        subjectToUpdate.setName(subjectViewModel.getName());
        subjectToUpdate.setDescription(subjectViewModel.getDescription());
        subjectRepository.save(subjectToUpdate);
    }
}
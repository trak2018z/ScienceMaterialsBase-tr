package com.dpiotr.repository;

import com.dpiotr.model.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dpiotr on 29.10.17.
 */
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Subject findById(Long id);
    List<Subject> findByName(String name);
}

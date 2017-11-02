package com.dpiotr.repository;

import com.dpiotr.model.Subject;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dpiotr on 29.10.17.
 */
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}

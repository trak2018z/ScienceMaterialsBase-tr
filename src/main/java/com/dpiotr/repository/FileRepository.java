package com.dpiotr.repository;

import com.dpiotr.model.File;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dpiotr on 29.10.17.
 */
public interface FileRepository extends CrudRepository<File, Long> {
    List<File> findAllBySubjectId(Long id);
    File findById(Long id);
    boolean existsByUrl(String url);
}

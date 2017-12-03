package com.dpiotr.repository;

import com.dpiotr.model.File;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dpiotr on 06.11.17.
 */
public interface CommentRepository extends CrudRepository<File, Long> {
}
package com.dpiotr.repository;

import com.dpiotr.model.File;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dpiotr on 29.10.17.
 */
public interface FileRepository extends CrudRepository<File,Long>{
}

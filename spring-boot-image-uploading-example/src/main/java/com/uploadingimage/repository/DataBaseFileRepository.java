package com.uploadingimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uploadingimage.entity.DataBaseFile;


@Repository
public interface DataBaseFileRepository extends JpaRepository<DataBaseFile, String> {

}

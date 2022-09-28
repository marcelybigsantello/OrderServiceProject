package com.masantello.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masantello.demo.models.OS;

@Repository
public interface OsRepository extends JpaRepository<OS, Integer>{

}

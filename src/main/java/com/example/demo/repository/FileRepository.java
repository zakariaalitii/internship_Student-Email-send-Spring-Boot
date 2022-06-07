package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Filesend;

public interface FileRepository extends  JpaRepository<Filesend, Long>{

}

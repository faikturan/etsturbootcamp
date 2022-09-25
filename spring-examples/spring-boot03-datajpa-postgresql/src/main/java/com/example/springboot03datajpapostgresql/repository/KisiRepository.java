package com.example.springboot03datajpapostgresql.repository;

import com.example.springboot03datajpapostgresql.entity.Kisi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KisiRepository extends JpaRepository<Kisi, Long> {
}

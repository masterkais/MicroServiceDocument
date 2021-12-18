package com.iit.imprimerie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iit.imprimerie.entities.Document;

@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {

	@Query(value = "SELECT * FROM Document u WHERE u.ens = :id", nativeQuery = true)
	public List<Document> getDocymentByEnsignant(@Param("id") int id);

	@Query(value = "SELECT * FROM Document u WHERE u.mat = :id", nativeQuery = true)
	public List<Document> getDocymentByMatiere(@Param("id") int id);

	public Document findByPath(String path);

}

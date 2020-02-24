package com.ss.lms.librarian.librarian.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ss.lms.librarian.librarian.entity.Branch;



@Repository
public interface BranchDAO extends JpaRepository<Branch, Long> {

}


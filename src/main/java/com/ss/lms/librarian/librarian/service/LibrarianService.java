package com.ss.lms.librarian.librarian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ss.lms.librarian.librarian.DAO.BranchDAO;
import com.ss.lms.librarian.librarian.entity.Branch;

@Component
public class LibrarianService {
	@Autowired
	BranchDAO branchDAO;

	public List<Branch> readAllBranches() {

		return branchDAO.findAll();
	}

	public Optional<Branch> readBranchsById(Integer branchId) {

		return branchDAO.findById(Long.valueOf(branchId));
	}
	
	@Transactional
	public Branch updateBranch(Branch branch) {
		
		return  branchDAO.save(branch);
	}

}

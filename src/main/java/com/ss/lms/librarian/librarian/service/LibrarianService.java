package com.ss.lms.librarian.librarian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.librarian.librarian.DAO.BranchDAO;
import com.ss.lms.librarian.librarian.entity.Branch;

@Component
public class LibrarianService {
	@Autowired
	BranchDAO branchDAO;

	public List<Branch> readAllBranches() throws Exception {

		List<Branch> listOfBranchs = null;

		try {

			listOfBranchs = branchDAO.findAll();
		} catch (Exception e) {

			throw e;
		} finally {

		}

		return listOfBranchs;
	}

	public Optional<Branch> readBranchsById(Integer branchId) throws Exception {

		Optional<Branch> branch = null;

		try {

			branch = branchDAO.findById(Long.valueOf(branchId));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

		}
		return branch;

	}

	public Branch updateBranch(Branch branch) {
		Branch updatedBranch = null;
		// TODO Auto-generated method stub
		try {
			updatedBranch = branchDAO.save(branch);
			branchDAO.flush();
		} catch (Exception e) {
			throw e;
		}
		
		return updatedBranch;

	}

}

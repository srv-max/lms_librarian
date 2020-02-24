package com.ss.lms.librarian.librarian.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.librarian.librarian.entity.Branch;
import com.ss.lms.librarian.librarian.service.LibrarianService;

@RestController
public class LibrarianController {
	@Autowired
	LibrarianService librarianService;

	@RequestMapping(path = "/librarian/branches", method = RequestMethod.GET)
	public ResponseEntity<List<Branch>> getAllBranches() {
		try {
			List<Branch> branches = librarianService.readAllBranches();
			return new ResponseEntity<List<Branch>>(branches, HttpStatus.OK);

		} catch (Exception e) {
			// e.printStackTrace();
			return new ResponseEntity<List<Branch>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/librarian/branches/{branchId}", method = RequestMethod.GET)
	public ResponseEntity<Branch> getBranchById(@PathVariable("branchId") Integer branchId) {
		Optional<Branch> branch = null;
		try {
			branch = librarianService.readBranchsById(branchId);

			if (!branch.isPresent()) {
				return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Branch>(branch.get(), HttpStatus.OK);

		} catch (Exception e) {
			// e.printStackTrace();
			return new ResponseEntity<Branch>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/librarian/branches/{branchId}", method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Branch> updateBranch(@RequestBody Branch branch, @PathVariable Integer branchId) {
		Optional<Branch> validateBranch = null;

		if (branch.getBranchId() == null || branch.getBranchName() == null || branch.getBranchAddress() == null) {
			return new ResponseEntity<Branch>(HttpStatus.BAD_REQUEST);
		}

		try {
			validateBranch = librarianService.readBranchsById(branch.getBranchId().intValue());

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Branch>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// checking if branch is valid
		if (!validateBranch.isPresent()) {
			return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
		}
		
		//checking id being passed are same
		if (branch.getBranchId().intValue() != branchId) {
			return new ResponseEntity<Branch>(HttpStatus.BAD_REQUEST);
		}

		// updating branch
		try {
			librarianService.updateBranch(branch);
			return new ResponseEntity<Branch>(branch, HttpStatus.ACCEPTED);
		}

		catch (Exception e) {
			return new ResponseEntity<Branch>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}

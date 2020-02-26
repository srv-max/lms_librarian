package com.ss.lms.librarian.librarian.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.librarian.librarian.entity.Branch;
import com.ss.lms.librarian.librarian.service.LibrarianService;

@RestController
@RequestMapping("librarian/")
public class LibrarianController {
	@Autowired
	LibrarianService librarianService;

	@GetMapping(path = "branches", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Branch>> getBranches() {

		List<Branch> branches = librarianService.readAllBranches();
		return new ResponseEntity<List<Branch>>(branches, HttpStatus.OK);

	}

	@GetMapping(path = "branches/{branchId}")
	public ResponseEntity<Branch> getBranchById(@PathVariable("branchId") Integer branchId) {
		Optional<Branch> branch = null;

		branch = librarianService.readBranchsById(branchId);

		if (!branch.isPresent()) {
			return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Branch>(branch.get(), HttpStatus.OK);

	}

	@PutMapping(path = "branches/{branchId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateBranch(@Valid @RequestBody Branch branch, @PathVariable Integer branchId) {

		Optional<Branch> validateBranch = librarianService.readBranchsById(branch.getBranchId().intValue());

		// checking if branch is valid
		if (!validateBranch.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// checking id being passed are same
		if (branch.getBranchId().intValue() != branchId) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		// updating branch

		librarianService.updateBranch(branch);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);

	}
}

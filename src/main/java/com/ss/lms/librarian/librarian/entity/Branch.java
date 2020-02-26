package com.ss.lms.librarian.librarian.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "tbl_library_branch")
public class Branch {
	
	@Id
	@NotNull
	@Column(name="branchId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long branchId;
	
	@NotBlank
	@Column(name="branchName", nullable = false)
	private String branchName;
	
	@NotBlank
	@Column(name="branchAddress", nullable = false)
	private String branchAddress;
	
	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
}


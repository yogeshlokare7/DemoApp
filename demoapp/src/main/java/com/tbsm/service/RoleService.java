package com.tbsm.service;

import java.util.List;
import java.util.Map;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.Role;

public interface RoleService {
	
	public Iterable<Role> getAllRoles();
	
	public Role saveRole(Role role);
	
	public Role updateRole(Role role);

	public Role getRoleById(Long roleId) throws ResourceNotFoundException;

	public Map<String, Boolean> removeRoleById(Long roleId) throws ResourceNotFoundException;
	
}
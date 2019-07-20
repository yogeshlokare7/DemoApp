package com.tbsm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.Role;
import com.tbsm.model.User;
import com.tbsm.repository.RoleRepository;
import com.tbsm.repository.UserRepository;
import com.tbsm.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository repo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public Iterable<Role> getAllRoles() {
		return repo.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public Role saveRole(Role role) {
		return repo.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		return repo.save(role);
	}

	@Override
	public Role getRoleById(Long roleId) throws ResourceNotFoundException {
		return repo.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
	}

	@Override
	public Map<String, Boolean> removeRoleById(Long roleId) throws ResourceNotFoundException {
		Role optional = getRoleById(roleId);
		Map<String, Boolean> response = new HashMap<>();
		if(optional!=null) {
			List<User> list = userRepo.findByRole(optional);
			if(list.size() <=0) {
				repo.delete(optional);
				response.put("deleted", Boolean.TRUE);
			}else{
				response.put("deleted", Boolean.FALSE);
			}
		}else {
			response.put("deleted", Boolean.FALSE);
		}
		return response;
	}

	@Override
	public Page<Role> listByPage(Pageable pageable) {
		return repo.findAll(pageable);
	}

}

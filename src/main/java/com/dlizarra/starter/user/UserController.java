package com.dlizarra.starter.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dlizarra.starter.role.RoleName;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public List<UserDto> findAll() {
		return userService.getUsers();
	}

}

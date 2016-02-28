package com.dlizarra.startuphub.user;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.CustomMapper;

@Component
public class UserDtoMapper extends CustomMapper<User, UserDto> {
	// @Override
	// public void mapAtoB(final User user, final UserDto userDto, final MappingContext context) {
	// userDto.getProjects().forEach(p -> {
	// if (p.getCreator() != null) {
	// p.getCreator().setProjects(new ArrayList<ProjectDto>());
	// }
	// p.setMembers(new ArrayList<UserDto>());
	// });

	// userDto.getProjects().forEach(p -> {
	// if (p.getCreator() != null) {
	// p.getCreator().setProjects(new ArrayList<ProjectDto>());
	// }
	// p.setMembers(new ArrayList<UserDto>());
	// });
	// }

}

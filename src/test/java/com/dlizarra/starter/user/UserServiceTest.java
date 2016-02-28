package com.dlizarra.starter.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.dlizarra.starter.support.AbstractUnitTest;
import com.dlizarra.starter.support.orika.OrikaBeanMapper;

@Transactional
public class UserServiceTest extends AbstractUnitTest {

	@Mock
	private UserRepository userRepository;

	@Autowired
	@Spy
	private OrikaBeanMapper mapper;

	@InjectMocks
	private UserServiceImpl userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		final User u1 = new User();
		u1.setId(1);
		u1.setUsername("david");
		final User u2 = new User();
		u2.setId(2);
		u2.setUsername("mark");
		final List<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);
		when(userRepository.findAll(any(Sort.class))).thenReturn(users);
		when(userRepository.findOne(1)).thenReturn(Optional.of(u1));
		when(userRepository.findOne(500)).thenReturn(Optional.empty());
	}

	@Test
	public void testFindAll_TwoUsersInDb_ShouldReturnTwoUsers() {
		// act
		final List<UserDto> users = userService.getUsers();
		// assert
		assertThat(users.size()).isEqualTo(2);
	}

	@Test
	public void testGetUser_ExistingIdGiven_ShouldReturnUser() {
		// act
		final UserDto u = userService.getUser(1);
		// assert
		assertThat(u.getUsername()).isEqualTo("david");
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetUser_NonExistingIdGiven_ShouldThrowUserNotFoundException() {
		userService.getUser(500);
	}

}

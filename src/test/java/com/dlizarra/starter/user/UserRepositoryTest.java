package com.dlizarra.starter.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.dlizarra.starter.support.AbstractWebIntegrationTest;

@Sql({ "classpath:/sql/cleanup.sql", "classpath:/sql/user.sql" })
public class UserRepositoryTest extends AbstractWebIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void save_UserGiven_ShouldSaveUser() {
		// arrange
		final User userStan = new User();
		userStan.setUsername("stan");
		userStan.setPassword("stanpass");
		// act
		userRepository.save(userStan);
		// assert
		assertThat(userStan.getId()).isNotNull();
	}

	@Test
	public void update_ExistingUserGiven_ShouldUpdateUser() {
		// arrange
		final User user = new User();
		user.setId(2);
		user.setUsername("albert");
		user.setPassword("albertpass");
		// act
		final User updatedUser = userRepository.save(user);
		// assert
		assertThat(updatedUser).isEqualTo(user);
	}

	@Test
	public void findOne_ExistingIdGiven_ShouldReturnUser() {
		// act
		final Optional<User> userOpt = userRepository.findOne(1);
		assertThat(userOpt.isPresent()).isTrue();
		final User user = userOpt.get();
		// assert
		assertThat(user.getUsername()).isEqualTo("david");
	}

	@Transactional
	@Test
	public void getOne_ExistingIdGiven_ShouldReturnLazyEntity() {
		// act
		final User user1 = userRepository.getOne(1);
		// assert
		assertThat(user1).isNotNull();
		assertThat(user1.getId()).isEqualTo(1);
	}

	@Sql({ "classpath:/sql/cleanup.sql", "classpath:/sql/user.sql" })
	@Test
	public void findAll_TwoUsersinDb_ShouldReturnTwoUsers() {
		// act
		final List<User> allUsers = userRepository.findAll();
		// assert
		assertThat(allUsers.size()).isEqualTo(2);
	}

	@Sql({ "classpath:/sql/cleanup.sql", "classpath:/sql/user.sql" })
	@Test
	public void delete_ExistingIdGiven_ShouldDeleteUser() {
		// act
		userRepository.delete(2);
		// assert
		assertThat(userRepository.findAll().size()).isEqualTo(1);
	}

}

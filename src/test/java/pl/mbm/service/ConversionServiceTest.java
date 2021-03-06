package pl.mbm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mbm.configuration.ConverterTestContext;
import pl.mbm.entity.User;
import pl.mbm.entity.UserBuilder;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.service.dto.UserRegistrationForm;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConverterTestContext.class, })
public class ConversionServiceTest {

	@Autowired
	private ConversionService conversionService;

	@Test
	public void convertTest() {
		UserJTable user = conversionService.convert(new UserBuilder().id(1L)
				.name("one").password("pass").email("asdf@asdf").build(),
				UserJTable.class);
		assertEquals("pass", user.getPassword());
		assertEquals("one", user.getName());
		assertEquals("asdf@asdf", user.getEmail());
	}

	@Test
	public void convertSetTest() {
		List<User> usersMock = new ArrayList<User>();
		usersMock.add(new UserBuilder().name("one").role("ROLE_USER").build());
		usersMock.add(new UserBuilder().name("two").build());

		List<UserJTable> users = (List<UserJTable>) conversionService.convert(
				usersMock,
				TypeDescriptor.forObject(usersMock),
				TypeDescriptor.collection(List.class,
						TypeDescriptor.valueOf(UserJTable.class)));

		assertEquals(2, users.size());
		assertTrue(users.get(0).getRoleUser());
		assertFalse(users.get(1).getRoleUser());
	}

	@Test
	public void covertToUser() {
		User user = conversionService.convert(new UserRegistrationForm(),
				User.class);
		assertNotNull(user);
	}
}

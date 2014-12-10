package pl.mbm.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.builder.UserRegistrationFormBuilder;
import pl.mbm.configuration.ConverterTestContext;
import pl.mbm.dao.RoleDao;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.model.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConverterTestContext.class })
public class UserConverterTest {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private RoleDao roleDaoMock;

	@Before
	public void setUp() {
		Mockito.reset(roleDaoMock);
	}

	@Test
	public void autowireRoleDaoTest() {
		assertNotNull(roleDaoMock);
	}

	@Test
	public void autowireConversionServiceTest() {
		assertNotNull(conversionService);
	}

	@Test
	public void convertUserRegistrationFormToUser() {
		when(roleDaoMock.findOneByName("ROLE_USER")).thenReturn(null);

		UserRegistrationForm userRegistrationForm = new UserRegistrationFormBuilder()
				.name("asdfasdf").password("asdfasdf")
				.confirmPassword("asdfasdf").email("asdf@asdf.asdf").build();
		User user = conversionService.convert(userRegistrationForm, User.class);
		assertEquals(1, user.getRoles().size());
	}

}

package pl.mbm.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.builder.UserBuilder;
import pl.mbm.configuration.ApplicationContext;
import pl.mbm.model.dto.UserJTable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class })
public class UserJTableConverterTest {

	@Autowired
	private ConversionService converter;

	@Test
	public void convertTest() {
		UserJTable user = converter.convert(new UserBuilder().id(1L)
				.name("one").password("pass").email("asdf@asdf").build(),
				UserJTable.class);
		assertEquals("pass", user.getPassword());
		assertEquals("one", user.getName());
		assertEquals("asdf@asdf", user.getEmail());
	}

}

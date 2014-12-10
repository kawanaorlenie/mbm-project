package pl.mbm.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.mbm.configuration.WebAppContext;
import pl.mbm.model.dto.UserJTable;
import pl.mbm.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebAppContext.class })
@WebAppConfiguration
public class UserManagementControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserService userServiceMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@InjectMocks
	private UserManagementController userManagementController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(userManagementController)
				.build();
	}

	@Test
	public void listUsers_UsersFound_ShouldReturnFoundUserEntries()
			throws Exception {

		UserJTable first = new UserJTable();
		first.setName("first");

		UserJTable second = new UserJTable();
		second.setName("second");

		when(userServiceMock.listUsers()).thenReturn(
				Arrays.asList(first, second));

		// TODO dlaczego status jest OK jak nie ma mapowania do /user/lis
		mockMvc.perform(
				get("/user/list").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.contentType(
										new MediaType(
												MediaType.APPLICATION_JSON
														.getType(),
												MediaType.APPLICATION_JSON
														.getSubtype(), Charset
														.forName("utf8"))))
				.andExpect(jsonPath("$", hasSize(2)));

		verify(userServiceMock, times(1)).listUsers();
		verifyNoMoreInteractions(userServiceMock);
	}

}

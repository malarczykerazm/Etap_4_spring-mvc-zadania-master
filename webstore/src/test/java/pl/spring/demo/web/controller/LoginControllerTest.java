package pl.spring.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.spring.demo.controller.LoginController;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	private MockMvc mockMvc;

	@Mock
	Principal user;

	@InjectMocks
	private LoginController loginController;

	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();
	}

	@Test
	public void shouldReturnTheLoginPage() throws Exception {

		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/login"));

		// then
		resultActions.andExpect(view().name("login"));
	}

	@Test
	public void shouldReturnTheLoginfailedPage() throws Exception {

		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/loginfailed"));

		// then
		resultActions.andExpect(view().name("login")).andExpect(model().attribute("error", "true"));
	}

	@Test
	public void shouldReturnTheLogoutPage() throws Exception {

		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/logout"));

		// then
		resultActions.andExpect(view().name("logout"));
	}

	@Test
	public void shouldReturnThe403AccecDeniedPage() throws Exception {

		// given
		String userName = "name";

		Mockito.when(user.getName()).thenReturn(userName);

		// when
		ResultActions resultActions = mockMvc.perform(get("/403").principal(user));

		// then
		resultActions.andExpect(view().name("403"));

	}

}

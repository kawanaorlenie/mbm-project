package pl.mbm.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import pl.mbm.builder.UserRegistrationFormBuilder;
import pl.mbm.model.dto.UserRegistrationForm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static byte[] convertObjectToJsonBytes(Object object)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	public static UserRegistrationForm getUserRegistrationForm() {
		return new UserRegistrationFormBuilder().name("user00")
				.password("password").confirmPassword("password")
				.email("user00@matrom.pl").build();
	}

	public static byte[] getUserRegistrationFormJsonBytes() throws IOException {
		return TestUtil.convertObjectToJsonBytes(getUserRegistrationForm());
	}
}

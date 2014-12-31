package pl.mbm.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import pl.mbm.converter.UserConverter;
import pl.mbm.converter.UserJTableConverter;

@Configuration
@ComponentScan(basePackageClasses = { UserConverter.class })
public class ConversionServiceFactoryBeanContext {

	@Autowired
	private UserConverter userConverter;

	@Bean
	public ConversionServiceFactoryBean conversionService() {
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(userConverter);
		converters.add(new UserJTableConverter());
		conversionServiceFactoryBean.setConverters(converters);
		return conversionServiceFactoryBean;
	}

}

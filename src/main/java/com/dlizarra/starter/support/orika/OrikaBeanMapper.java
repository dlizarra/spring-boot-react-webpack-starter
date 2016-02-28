package com.dlizarra.starter.support.orika;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

/**
 * Orika mapper exposed as a Spring Bean. It contains the configuration for the mapper factory and factory builder. It
 * will scan the Spring application context searching for mappers and converters to register them into the factory. To
 * use it just autowire it into a class.
 *
 * @author dlizarra
 */
@Component
public class OrikaBeanMapper extends ConfigurableMapper implements ApplicationContextAware {

	private MapperFactory factory;
	private ApplicationContext applicationContext;

	public OrikaBeanMapper() {
		super(false);
	}

	@Override
	protected void configure(final MapperFactory factory) {
		this.factory = factory;
		addAllSpringBeans(applicationContext);
	}

	@Override
	protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
		factoryBuilder.mapNulls(false);
	}

	/**
	 * Constructs and registers a {@link ClassMapBuilder} into the {@link MapperFactory} using a {@link Mapper}.
	 *
	 * @param mapper
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addMapper(final Mapper<?, ?> mapper) {
		factory.classMap(mapper.getAType(),
				mapper.getBType())
				.byDefault()
				.customize((Mapper) mapper)
				.mapNulls(false)
				.mapNullsInReverse(false)
				.register();
	}

	/**
	 * Registers a {@link Converter} into the {@link ConverterFactory}.
	 *
	 * @param converter
	 */
	public void addConverter(final Converter<?, ?> converter) {
		factory.getConverterFactory().registerConverter(converter);
	}

	/**
	 * Scans the appliaction context and registers all Mappers and Converters found in it.
	 *
	 * @param applicationContext
	 */
	@SuppressWarnings("rawtypes")
	private void addAllSpringBeans(final ApplicationContext applicationContext) {
		final Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
		for (final Mapper mapper : mappers.values()) {
			addMapper(mapper);
		}
		final Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
		for (final Converter converter : converters.values()) {
			addConverter(converter);
		}
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		init();
	}

}
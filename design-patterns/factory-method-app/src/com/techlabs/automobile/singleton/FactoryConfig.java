package com.techlabs.automobile.singleton;

import java.util.*;

public class FactoryConfig {
	private IConfigLoader configLoader;
	private String configPath;
	private Map<String, String> config;
	private Class<?> factoryClass;
	
	public FactoryConfig()
	{
		this(new ProperitesLoader(), "assets/factory.properties");
	}
	
	public FactoryConfig(IConfigLoader configLoader, String configPath)
	{
		this.setConfigLoader(configLoader);
		this.setConfigPath(configPath);
		this.loadPropsData();
	}
	
	public void setConfigLoader(IConfigLoader configLoader)
	{
		if (configLoader == null)
			configLoader = new ProperitesLoader();
		this.configLoader = configLoader;
	}
	
	public void setConfigPath(String path)
	{
		this.configPath = path;
	}
	
	public void loadPropsData()
	{
		this.config = this.configLoader.loadConfig(this.configPath);
		setFactoryClass();
	}
	
	private void setFactoryClass()
	{
		if (config == null)
			loadPropsData();
		this.factoryClass = Reflector.getClass(config.get("factory.name"));
		if (this.factoryClass == null)
			this.factoryClass = BMWFactory.class;
	}
	
	public IAutoFactory getFactory()
	{
		if (this.factoryClass == null)
			setFactoryClass();
		IAutoFactory factory = (IAutoFactory)Reflector.invokeStaticMethod(
				this.factoryClass, "getInstance");
		if (factory == null)
			return BMWFactory.getInstance();
		return factory;
	}
}

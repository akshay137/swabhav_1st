package com.techlabs.dom;

public class DOMControl implements IDOMElement {
	
	private String name;
	private String attributes;
	private String innerHTML;
	
	public DOMControl(String name, String innerHTML, String attributes)
	{
		this.name = name;
		this.innerHTML = innerHTML;
		this.attributes = attributes;
	}
	
	public DOMControl(String name, String innerHTML)
	{
		this(name, innerHTML, "");
	}
	
	public DOMControl(String name)
	{
		this(name, "");
	}

	@Override
	public String build() {
		StringBuilder builder = new StringBuilder("");
		builder.append('<');
		builder.append(this.name);
		if (this.attributes.length() > 0)
			builder.append(' ');
		builder.append(this.attributes);
		builder.append('>');
		
		builder.append(this.innerHTML);
		
		builder.append("</");
		builder.append(this.name);
		builder.append(">\n");
		return builder.toString();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getInnerHTML()
	{
		return this.innerHTML;
	}
	
	public String getAttributes()
	{
		return this.attributes;
	}
	
	public void setInnerHTML(String innerHTML)
	{
		this.innerHTML = innerHTML;
	}
	
	public void setAttributes(String attributes)
	{
		this.attributes = attributes;
	}
}

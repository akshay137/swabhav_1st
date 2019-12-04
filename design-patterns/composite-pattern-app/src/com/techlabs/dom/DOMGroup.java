package com.techlabs.dom;

import java.util.*;

public class DOMGroup implements IDOMElement {

	private String name;
	private List<IDOMElement> children;
	
	public DOMGroup(String name) {
		this.name = name;
		this.children = new ArrayList<IDOMElement>();
	}
	
	@Override
	public String build() {
		StringBuilder builder = new StringBuilder("");
		builder.append('<');
		builder.append(this.name);
		builder.append(">\n");
		
		for (IDOMElement element : this.children)
			builder.append(element.build());
		
		builder.append("</");
		builder.append(this.name);
		builder.append(">\n");
		return builder.toString();

	}

	public void add(IDOMElement element) {
		this.children.add(element);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public List<IDOMElement> getChildren()
	{
		return this.children;
	}

}

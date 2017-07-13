package com.qhit.model;

/**
 * 商品分类实体类
 * 
 * @author Administrator
 * 
 */
public class GoodType {

	private int typeId;
	private String typeName;// 分类名称

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
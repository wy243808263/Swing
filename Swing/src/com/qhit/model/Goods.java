package com.qhit.model;

import java.math.BigDecimal;

/**
 * 商品信息实体类
 * 
 * @author Administrator
 * 
 */
public class Goods {

	private int gid;
	private String goodNo;// 产品编号
	private String goodName;// 产品名称
	private BigDecimal goodPrice;// 产品价格
	private int goodIntegral;// 产品积分
	private int typeId;// 产品类型ID
	private int goodNum;// 商品数量
	private String typeName;// 分类名称

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGoodNo() {
		return goodNo;
	}

	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public BigDecimal getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(BigDecimal goodPrice) {
		this.goodPrice = goodPrice;
	}

	public int getGoodIntegral() {
		return goodIntegral;
	}

	public void setGoodIntegral(int goodIntegral) {
		this.goodIntegral = goodIntegral;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}
}
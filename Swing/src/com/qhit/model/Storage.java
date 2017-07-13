package com.qhit.model;

import java.math.BigDecimal;

/**
 * 商品入库实体类
 * 
 * @author Administrator
 * 
 */
public class Storage {

	private int id;
	private int goodsId;// 商品编号
	private String storhead;// 负责人
	private String storageDate;// 录入时间
	private BigDecimal goodPrice;// 商品价格
	private Integer goodIntegral;// 产品积分

	private String goodNo;// 商品编号
	private String goodName;// 商品名称
	private String typeName;// 类型名称
	private int goodNum;// 商品数量

	public Integer getGoodIntegral() {
		return goodIntegral;
	}

	public void setGoodIntegral(Integer goodIntegral) {
		this.goodIntegral = goodIntegral;
	}

	public BigDecimal getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(BigDecimal goodPrice) {
		this.goodPrice = goodPrice;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getStorhead() {
		return storhead;
	}

	public void setStorhead(String storhead) {
		this.storhead = storhead;
	}

	public String getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}
}
package com.qhit.model;

import java.math.BigDecimal;

/**
 * 产品销售实体类
 * 
 * @author Administrator
 * 
 */
public class SellInfo {

	private int SIid;
	private int Pid;// 产品ID
	private int Num;// 销售数量
	private String OutDate;// 销售日期

	private String goodNo;// 商品编号
	private String goodName;// 商品名称
	private String typeName;// 类型名称
	private BigDecimal goodPrice;// 商品价格
	private int goodIntegral;// 产品积分

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

	public int getSIid() {
		return SIid;
	}

	public void setSIid(int sIid) {
		SIid = sIid;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public String getOutDate() {
		return OutDate;
	}

	public void setOutDate(String outDate) {
		OutDate = outDate;
	}
}
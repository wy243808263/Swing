package com.qhit.model;

/**
 * 销售实体类
 * 
 * @author Administrator
 * 
 */
public class Sale {

	private int id;
	private int goodsId;// 商品编号
	private int saleNum;// 数量
	private String saleDate;// 日期

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

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
}
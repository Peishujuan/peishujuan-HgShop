package com.peishujuan.hgshop.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 订单详情
 * @author a'su's
 *
 */
public class OrderDetail implements Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 6656054883847709294L;

		private Integer id;
		
		private int skuid;
		
		private BigDecimal total;//该条的价格
		
		private int oid;
		
		private int pnum;
		
		private Sku sku;
		
		public OrderDetail() {
			super();
		}
		
		/**
		 * 
		 * @param oid  主表的id
		 * @param cart 购物车数据
		 */
		public OrderDetail(int oid,Cart cart) {
			
			super();
			this.oid=oid;
			this.pnum=cart.getPnum();
			this.total=cart.getSumTotal();
			this.skuid=cart.getSkuid();
			
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public int getSkuid() {
			return skuid;
		}
		public void setSkuid(int skuid) {
			this.skuid = skuid;
		}
		public BigDecimal getTotal() {
			return total;
		}
		public void setTotal(BigDecimal total) {
			this.total = total;
		}
		public int getOid() {
			return oid;
		}
		public void setOid(int oid) {
			this.oid = oid;
		}
		public int getPnum() {
			return pnum;
		}
		public void setPnum(int pnum) {
			this.pnum = pnum;
		}
		
		
		public Sku getSku() {
			return sku;
		}

		public void setSku(Sku sku) {
			this.sku = sku;
		}

		@Override
		public String toString() {
			return "OrderDetail [id=" + id + ", skuid=" + skuid + ", total=" + total + ", oid=" + oid + ", pnum=" + pnum
					+ ", sku=" + sku + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + oid;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OrderDetail other = (OrderDetail) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (oid != other.oid)
				return false;
			return true;
		}
}

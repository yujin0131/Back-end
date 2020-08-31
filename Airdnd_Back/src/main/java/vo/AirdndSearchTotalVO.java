package vo;

public class AirdndSearchTotalVO {
	private int average_price, data_total;

	public AirdndSearchTotalVO() {

	}

	public AirdndSearchTotalVO(int average_price, int data_total) {
		this.average_price = average_price;
		this.data_total = data_total;
	}

	public int getAverage_price() {
		return average_price;
	}

	public void setAverage_price(int average_price) {
		this.average_price = average_price;
	}

	public int getData_total() {
		return data_total;
	}

	public void setData_total(int data_total) {
		this.data_total = data_total;
	}


} 

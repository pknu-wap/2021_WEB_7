package egovframework.platform.module.kobis.vo;

public class BodyVO {
	private ItemsVO items;
	private String dataType;
	private int numOfRows;
	private int pageNo;
	private int totalCount;

	public ItemsVO getItems() {
		return items;
	}

	public void setItems(ItemsVO items) {
		this.items = items;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getNumOfRows() {
		return numOfRows;
	}

	public void setNumOfRows(int numObRows) {
		this.numOfRows = numObRows;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}

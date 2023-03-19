package issuebookpublisher;

public interface IssueBookPublish {
	
	public String publishIssueBookService();
	public void issueBooks(int iid, int uid, int bid, String iDate, String period, int sid); 
	public void viewIssuedBooks();
	public void returnBooks(int riid, String rDate, int overdueDays);                //riid = issue id... fine should be calculated
	public void viewReturnBooks();
	public int getIid();
	public void setIid(int iid);
	public int getUid();
	public void setUid(int uid);
	public int getBid();
	public void setBid(int bid);
	public String getiDate();
	public void setiDate(String iDate);
	public String getPeriod();
	public void setPeriod(String period);
	public int getSid();
	public void setSid(int sid);
	
}

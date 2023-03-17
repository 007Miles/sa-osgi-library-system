package issuebookpublisher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IssueBookPublishImpl implements IssueBookPublish {
	
//	String path = "C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\Issue Books.txt"; 
	String path = "Issue Books.txt";
//	String path = "Issue Books.txt";
	String line = "";
	
	private int iid;
	private int uid;
	private int bid;
	private int sid;
	private String iDate;
	private String period;
	private int riid;
	private String rDate; 
	private int overdueDays;
	
	private static Scanner s;
	
	@Override
	public String publishIssueBookService() {
		return "Enter the relevant details... RS.20 of a fine will be charged for a single overdue day";
	}


	@Override
	public void issueBooks(int iid, int uid, int bid, String iDate, String period, int sid) {
		
		this.setIid(iid);
		this.setUid(uid);
		this.setBid(bid);
		this.setiDate(iDate);
		this.setPeriod(period);
		this.setSid(sid);
		
		File ibooks = new File(path);
		try {
//			PrintWriter out = new PrintWriter(ibooks);
			BufferedWriter out = new BufferedWriter(new FileWriter(ibooks,true));
			out.append(iid + "," + uid + "," + bid + "," + iDate + ",Pending," + period + ",Pending" + "," + sid);
			out.newLine();
			System.out.println();
			System.out.println("Book " + bid + "(bid) Successfully issued by Staff member: " + sid + " to User id: " + uid + " on " + iDate + " for " + period + " days...\n");
			out.close();
		} catch (Exception e) {
			System.err.println("Can't Issue the book: " + e.getMessage()); 
		}
		
	}

	@Override
	public void viewIssuedBooks() {
		
		System.out.println();
		
		{	

			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					System.out.println("IID: " + values[0] + " | UID: " + values[1] + " | BID: " + values[2] + " | ISSUED DATE: " + values[3] + " | PERIOD: " + values[5] + " | ISSUED BY(SID): " + values[7] + " | ");
					
				}
				br.close();
				
			} catch (Exception e) {
				System.err.println("Can't read Issued book list: " + e.getMessage()); 
			} 
		}
		System.out.println();
	}


	@Override
	public void returnBooks(int riid, String rDate, int overdueDays) {
		this.riid = riid;
		this.rDate = rDate;
		this.overdueDays = overdueDays;
		
		int fine = overdueDays * 20;
		System.out.println("\nTotal fine charged for Issue id: " + riid + " is Rs." + fine + ".00\n");
		
//		String tempFile = "C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\temp file.txt";
		String tempFile = "temp file.txt";
		String Ifile = "C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\Return Books.txt";
		File oldFile = new File(path);
		File newFile = new File(tempFile);
		int retID = 0;
		int userID = 0;
		int bookID = 0;
		String issueDate = "";
		String retDate = "";
		String per = "";
		int staffID = 0;
		int overFine = 0;
		try {
			FileWriter fw = new FileWriter(tempFile);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			s= new Scanner(new File(path));
			s.useDelimiter("[,\n]");
			
			String retIDs = Integer.toString(retID);
			String userIDs = Integer.toString(userID);
			String bookIDs = Integer.toString(bookID);
			String odues = Integer.toString(overFine);
			String stID = Integer.toString(staffID);
			
			while(s.hasNext()) {
				
				retIDs = s.next();
				userIDs = s.next();
				bookIDs = s.next();
				issueDate = s.next();
				retDate = s.next();
				per = s.next();
				odues = s.next();
				stID = s.next();
				
				if (Integer.parseInt(retIDs) == riid) {
					pw.println(riid + "," + userIDs + "," + bookIDs + "," + issueDate + "," + rDate + "," + per + "," + fine + "," + stID );

				}
				else {
					pw.println(retIDs + "," + userIDs + "," + bookIDs + "," + issueDate + "," + retDate + "," + per + "," + odues + "," + stID );
					
				}
			}
//			Path delPath = Paths.get("C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\Issue Books.txt");
			
			s.close();
			pw.flush();
			pw.close();
			bw.close();
			fw.close();
			oldFile.delete();
//			Files.delete(delPath);
//			Files.deleteIfExists(Paths.get("C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\Issue Books.txt"));
			File dump = new File(path);
			newFile.renameTo(dump);

			
		} 
		catch (Exception e) {
			System.err.println("Temp file not deleted: " + e.getMessage()); 
		}
		

		System.out.println("Returned books list updated!!!\n");
	}


	@Override
	public void viewReturnBooks() {

		System.out.println();
		
		{
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					System.out.println("IID: " + values[0] + " | UID: " + values[1] + " | BID: " + values[2] + " | ISSUED DATE: " + values[3] + " | RETURN DATE: " + values[4] + " | PERIOD: " + values[5] + " | FINE: " + values[6] + " | ");
					
				}
				br.close();
				
			} catch (Exception e) {
				System.err.println("Can't read Returned book list: " + e.getMessage()); 
			}
//				catch (FileNotFoundException e) {
//				e.printStackTrace(); 
//			} catch (IOException e) {
//				e.printStackTrace(); 
//			} 
		}
		
	}


	public int getIid() {
		return iid;
	}


	public void setIid(int iid) {
		this.iid = iid;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
	}


	public String getiDate() {
		return iDate;
	}


	public void setiDate(String iDate) {
		this.iDate = iDate;
	}


	public String getPeriod() {
		return period;
	}


	public void setPeriod(String period) {
		this.period = period;
	}


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


}

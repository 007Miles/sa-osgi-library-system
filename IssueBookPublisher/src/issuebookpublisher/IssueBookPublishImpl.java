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
import java.nio.file.Paths;
import java.util.Scanner;

public class IssueBookPublishImpl implements IssueBookPublish {
	
	String path = "C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\Issue Books.txt"; 
//	String path = "Issue Books.txt";
	String line = "";
	
	private int iid;
	private int uid;
	private int bid;
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
	public void issueBooks(int iid, int uid, int bid, String iDate, String period) {
		
		this.setIid(iid);
		this.setUid(uid);
		this.setBid(bid);
		this.setiDate(iDate);
		this.setPeriod(period);
		
		File ibooks = new File(path);
		try {
//			PrintWriter out = new PrintWriter(ibooks);
			BufferedWriter out = new BufferedWriter(new FileWriter(ibooks,true));
			out.append("\n"+ iid + "," + uid + "," + bid + "," + iDate + ",Pending," + period + ",Pending");
			out.close();
			System.out.println();
			System.out.println("Book " + bid + "(bid) Successfully issued to User id: " + uid + " on " + iDate + " for " + period + " days...\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
					System.out.println("IID: " + values[0] + " | UID: " + values[1] + " | BID: " + values[2] + " | ISSUED DATE: " + values[3] + " | PERIOD: " + values[5] + " | ");
					
				}
				br.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace(); 
			} catch (IOException e) {
				e.printStackTrace(); 
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
		
		String tempFile = "C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\temp file.txt";
		String Ifile = "C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\Return Books.txt";
		File oldFile = new File(path);
		File newFile = new File(tempFile);
		int retID = 0;
		int userID = 0;
		int bookID = 0;
		String issueDate = "";
		String retDate = "";
		String per = "";
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
			
			while(s.hasNext()) {
				
				retIDs = s.next();
				userIDs = s.next();
				bookIDs = s.next();
				issueDate = s.next();
				retDate = s.next();
				per = s.next();
				odues = s.next();
				
				if (Integer.parseInt(retIDs) == riid) {
					pw.println(riid + "," + userIDs + "," + bookIDs + "," + issueDate + "," + rDate + "," + per + "," + fine);

				}
				else {
					pw.println(retIDs + "," + userIDs + "," + bookIDs + "," + issueDate + "," + retDate + "," + per + "," + odues);

				}
			}
			s.close();
			pw.flush();
			pw.close();
			oldFile.delete();
//			Files.deleteIfExists(Paths.get("C:\\Users\\HP\\Desktop\\SLIIT\\3Y 1S\\SA - SE3030\\Assignment 01\\Issue Books.txt"));
			File dump = new File(path);
			newFile.renameTo(dump);
			
		} 
		catch (Exception e) {
			e.printStackTrace(); 
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
				
			} catch (FileNotFoundException e) {
				e.printStackTrace(); 
			} catch (IOException e) {
				e.printStackTrace(); 
			} 
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


}

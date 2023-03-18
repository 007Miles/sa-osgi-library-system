package memberpublisher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;



import java.util.List;



@SuppressWarnings("unused")
public class MemberServicelmpl implements MemberService {

	private Scanner scan;
	private ArrayList<String[]> list;
	
	private static final String FILE_NAME = "F:\\3_rd_Year\\1_st_Sem\\Software_Architecture_SE3030\\Project_git\\SA_OSGI_Library_System\\MemberPublisher\\src\\memberpublisher\\member_data.txt";
	
	
	
	private List<Member> memberList = new ArrayList<>();
	
	public MemberServicelmpl() {
		
		scan = new Scanner(System.in);
		list = new ArrayList<String[]>();
		
	}
	
	@SuppressWarnings("unused")
	@Override
	public void insertMemberDetails() {
		String firstName, lastName, phoneNumber, email, dob, address, nic;
		int staffid;
		
		System.out.println("\n------- Register New Member To System --------");
		System.out.print("Enter Member's first name: ");
		firstName = scan.nextLine();
		System.out.print("Enter Member's last name: ");
		lastName = scan.nextLine();
		System.out.print("Enter Member's birth date: ");
		dob = scan.nextLine();
		System.out.print("Enter Member's NIC: ");
		nic = scan.nextLine();
		System.out.print("Enter Member's contact number: ");
		phoneNumber = scan.nextLine();
		System.out.print("Enter Member's email: ");
		email = scan.nextLine();
		System.out.print("Enter Member's address: ");
		address = scan.nextLine();
		
		System.out.print("Enter Staff Officer ID to approve : ");
		staffid = scan.nextInt();
		 
	    int memberId = 0;
		try {
			memberId = getNextId();
		} catch (IOException e1) {
	
			e1.printStackTrace();
		}

		Member newMember = new Member(memberId, firstName,lastName,dob,nic,phoneNumber, email, address, staffid);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME,true));){
   
            writer.write(newMember.getMemberId() + "," + newMember.getFirstName() + "," + newMember.getLastName() + "," + newMember.getDob() + "," + newMember.getNic() + "," + newMember.getPhoneNumber() + "," + newMember.getEmail() + "," + newMember.getAddress() + "," + newMember.getStaffId() +"\n");

            System.out.println("Member : " + newMember.getFirstName() + " " + "added successfully. Approved by " + newMember.getStaffId() + " Staff Officer" );
			writer.close();
		
            
            
        } catch (IOException e) {
            System.err.println("Error adding new Member to system: " + e.getMessage());
        }
		
		
	}
	
	private int getNextId() throws IOException {
        int memberId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0]);
                if (currentId >= memberId) {
                	memberId = currentId + 1;
                }
            }
        }
        return memberId;
    }

	

	@Override
	public void searchMemberDetails(String keyword) {
		
				int count = 0;
				List<Member> MemberAvailabales = this.getMemberAll();
				
				for(Member member: MemberAvailabales) {
					if(member.getFirstName().contains(keyword) || member.getLastName().contains(keyword) 
							|| member.getNic().contains(keyword) || member.getAddress().contains(keyword) || member.getDob().contains(keyword) || member.getEmail().contains(keyword)
							|| member.getPhoneNumber().contains(keyword)) {
						System.out.println(count + ". "+ "Member ID :" + member.getMemberId() + "| First Name : " + member.getFirstName()
						+" "+ "| NIC :" + member.getNic() + " "+ "| Phone Number" + member.getPhoneNumber()+" " + "| Email :" + member.getEmail()) ;
						count++;
					}
				}
				
				for(int i=0; i<list.size(); i++) {
					if(list.get(i)[0].contains(keyword) ||  list.get(i)[1].contains(keyword)) {
						System.out.println(count + ". " + list.get(i)[0]);
						count++;
					}
				}
		
	}
	
	 
	 @Override
		public boolean getAvailabilityByID(int memberid) {

		 try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	String[] parts = line.split(",");
	                int mId = Integer.parseInt(parts[0]);
	                
	                if (mId == memberid) {
	                    return true;
	                }
	               
	            }
	        } catch (IOException e) {
	            System.err.println("Error " + e.getMessage());
	        }
	        return false;
		}
	 
	 
	 @SuppressWarnings("resource")
	@Override
	    public void deleteMember(String dnic) {
		 List<Member> memberListtoDelete = this.getMemberAll();

			@SuppressWarnings("resource")
			Scanner scan2 = new Scanner(System.in);

			String dfirstName = ""; String dlastName = ""; String nic = ""; String ddob = ""; String demail = ""; String dphoneNumber = "";  String dAddress = ""; 
			
			try(BufferedWriter writer  = new BufferedWriter(new FileWriter(FILE_NAME,false));) {
				
				for(Member member: memberListtoDelete) {
					
					if(member.getNic().equals(dnic)) {
						
											
					}
					else {
					
					writer.write(member.getMemberId()+","+member.getFirstName()+","+ member.getLastName()+","+","+ member.getNic()+","+ member.getDob()+","+ member.getEmail()+","+ member.getPhoneNumber()+","+ member.getAddress()+ "," + member.getStaffId()+ "\n" );
					}
				}
				
				System.out.println("\nMember Deleted Successfully.\n");
				writer.close();
				
			}catch(IOException e){
				System.out.println("Writing error: " + e.getMessage());
				
			}
	 }
		 
	 
	 @SuppressWarnings("finally")
	@Override
		public List<Member> getMemberAll() {

			List<Member> memberList = new ArrayList<>();
			
	        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
	            String line;
	            
	            while ((line = reader.readLine()) != null) {
	            	
	                String[] parts = line.split(",");
	                int memberId = Integer.parseInt(parts[0]);
	                String firstName = parts[1];
	                String lastName = parts[2];
	                String dob = parts[3];
	                String nic = parts[4];
	                String phoneNumber = parts[5];
	                String email = parts[6];
	                String address = parts[7];
	                int staffid = Integer.parseInt(parts[8]);
	                Member member = new Member(memberId, firstName, lastName, dob, nic, phoneNumber, email, address, staffid);
	                memberList.add(member);
	                
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading Member data from file: " + e.getMessage());
	        }finally {
	        	return memberList;
	        }     
			
		}

	

	
	public void editMemberDetails(String nic) {

		List<Member> memberListtoUpdate = this.getMemberAll();

		@SuppressWarnings("resource")
		Scanner scan2 = new Scanner(System.in);
		
		String dfirstName = ""; String dlastName = ""; String dnic = ""; String ddob = ""; String tbEmail = ""; String tbPhoneNumber = "";  String tbAddress = ""; 
		
		try(BufferedWriter writer  = new BufferedWriter(new FileWriter(FILE_NAME,false));) {
			
			for(Member member: memberListtoUpdate) {
				
				if(member.getNic().equals(nic)) {
					System.out.println("Enter new Phone Number: ");
					tbPhoneNumber = scan2.nextLine();
					System.out.println("Enter new Email : ");
					tbEmail = scan2.nextLine();
					System.out.println("Enter new Address: ");
					tbAddress = scan2.nextLine();

					System.out.println(member.getNic());
					writer.write(member.getMemberId()+","+member.getFirstName()+","+member.getLastName()+","+member.getNic()+","+member.getDob()+","+tbEmail+","+tbPhoneNumber+","+ tbAddress+ "," + member.getStaffId() + "\n" );
					
										
				}
				else {
				
					writer.write(member.getMemberId()+","+member.getFirstName()+","+ member.getLastName()+","+","+ member.getNic()+","+ member.getDob()+","+ member.getEmail()+","+ member.getPhoneNumber()+","+ member.getAddress()+ "," + member.getStaffId() + "\n" );
				}
			}
			
			System.out.println("\nMember Updated Successfully.\n");
			writer.close();
			
		}catch(IOException e){
			System.out.println("Writing error: " + e.getMessage());
			
		}
	}
}

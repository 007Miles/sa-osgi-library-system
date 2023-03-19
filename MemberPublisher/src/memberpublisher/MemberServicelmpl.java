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
	
	private static final String FILE_NAME = "F:\\3_rd_Year\\1_st_Sem\\Software_Architecture_SE3030\\Project_update\\MemberPublisher\\src\\memberpublisher\\member_data.txt";
	
	
	private List<Member> memberList = new ArrayList<>();
	
	public MemberServicelmpl() {
		
		scan = new Scanner(System.in);
		list = new ArrayList<String[]>();
		
		String member1[] = {"sheron", "john", "2001.10.14", "0774521425", "sheron@gmail.com", "Colombo", "521452142515"};
		String member2[] = {"Kasun", "perera", "1999.11.24", "0755214515", "kasun@gmail.com", "Gampaha", "4521452142145"};
		
		list.add(member1);
		list.add(member2);
		
	}
	
	@SuppressWarnings("unused")
	@Override
	public void insertMemberDetails() {
		String firstName, lastName, phoneNumber, email, dob, address, nic;
		
		
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
		
	    int memberId = 0;
		try {
			memberId = getNextId();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//	
		String tempMember[] = {firstName, lastName, dob, nic, phoneNumber, email, address};
		Member newMember = new Member(memberId, firstName,lastName,dob,nic,phoneNumber, email, address);
//		this.list.add(tempMember);
		
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME,true));){
               
//            System.out.print("Enter Member's first name: ");
//            String firstName = reader.readLine();
//            
//            System.out.print("Enter Member's last name:  ");
//            String lastName = reader.readLine();
//            
//            System.out.print("Enter Member's Birth date:  ");
//            String dob = reader.readLine();
//            
//            System.out.print("Enter Member's NIC:  ");
//            String nic = reader.readLine();
//            
//            System.out.print("Enter Member's Contact Number:  ");
//            String phoneNumber = reader.readLine();
//            
//            System.out.print("Enter Member's Email:  ");
//            String email = reader.readLine();
//            
//            System.out.print("Enter Member's Address:  ");
//            String address = reader.readLine();
            
        
            

            
            writer.write(newMember.getMemberId() + "," + newMember.getFirstName() + "," + newMember.getLastName() + "," + newMember.getDob() + "," + newMember.getNic() + "," + newMember.getPhoneNumber() + "," + newMember.getEmail() + "," + newMember.getAddress() + "\n");
            
//            System.out.println(newMember.getFirstName());
			
            System.out.println("Member : " + newMember.getFirstName() + "added successfully.");
			writer.close();
		
            
            
        } catch (IOException e) {
            System.err.println("Error adding new Member to system: " + e.getMessage());
        }
		
		
//		Member member = new Member(firstName, lastName, dob, phoneNumber, email, address, nic);
		
//		String sqlQuery = "INSERT INTO Members(first_name, last_name, phone_number, dob, email, address) "
//				+ "VALUES('"+ member.getFirstName() +"', '"+ member.getLastName() +"', '"+ member.getPhoneNumber() +"', "
//						+ "'"+ member.getDob() +"', '"+ member.getEmail() + "', '"+ member.getAddress() + "')";
//		
//		try {
//			statement = connection.createStatement();
//			statement.executeUpdate(sqlQuery);
//			System.out.println("Member details successfully inserted ...");
//		} catch (SQLException exc) {
//			System.out.println("Issue with inserting doctor details !!!");
//			System.out.println(exc.getMessage());
//		}
		
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

	
	
//	@Override
//	public List<Member> getAllMemberDetails(){
//		
//		List<Member> memberList = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                int memberId = Integer.parseInt(parts[0]);
//                String firstName = parts[1];
//                String lastName = parts[2];
//                String dob = parts[3];
//                String nic = parts[4];
//                String phoneNumber = parts[5];
//                String email = parts[6];
//                String address = parts[7];
//                Member member = new Member(memberId, firstName, lastName, dob, nic, phoneNumber, email, address);
//                memberList.add(member);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading member data from file, " + e.getMessage());
//        }
//        return memberList;
//		
////		for(int i=0; i<list.size(); i++) {
////			System.out.println(i+1 + ". " + list.get(i)[0]);	
////			
////		}
		
//	}
	
	@Override
	public void searchMemberDetails(String keyword) {
		
//		int count = 0;
//		
//		for(int i=0; i<list.size(); i++) {
//			if(list.get(i)[0].contains(nic) ||  list.get(i)[1].contains(nic)) {
//				System.out.println(count + ". " + list.get(i)[0]);
//				count++;
//			}
//		}
		
		// TODO Auto-generated method stub
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
	
//	@Override
//    public void updateMemberDetails() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter Member ID to Update: ");
//        int memberid = scanner.nextInt();
//        scanner.nextLine();
//
//        Member member = getMemberById(memberid);
//        if (member == null) {
//            System.out.println("Member with memberID " + memberid + " not found.");
//            return;
//        }
//
//        System.out.print("Enter new Phone Number for Member: ");
//        String phoneNumber = scanner.nextLine();
//        member.setPhoneNumber(phoneNumber);
//
//
//        System.out.println("Member updated successfully.");
//    }
	
	 @Override
	    public Member getMemberById(int memberid) {
	        for (Member member : memberList) {
	            if (member.getMemberId() == memberid) {
	                return member;
	            }
	        }
	        return null;
	    }
	 
	 @Override
		public boolean getAvailabilityByID(int memberid) {
//		 List<Member> MemberAvailabales = this.getMemberAll();
			// TODO Auto-generated method stub
		 
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
			
//			String memberid = Integer.toString(dmemberid);
			
			String dfirstName = ""; String dlastName = ""; String nic = ""; String ddob = ""; String demail = ""; String dphoneNumber = "";  String dAddress = ""; 
			
			try(BufferedWriter writer  = new BufferedWriter(new FileWriter(FILE_NAME,false));) {
				
				for(Member member: memberListtoDelete) {
					
					if(member.getNic().equals(dnic)) {
						
											
					}
					else {
					
					writer.write(member.getMemberId()+","+member.getFirstName()+","+ member.getLastName()+","+","+ member.getNic()+","+ member.getDob()+","+ member.getEmail()+","+ member.getPhoneNumber()+","+ member.getAddress()+ "\n" );
					}
				}
				
			
				
				System.out.println("\nMember Deleted Successfully....\n");
				writer.close();
				
			}catch(IOException e){
				System.out.println("Writing error: " + e.getMessage());
				
			}
	 }
		 
//		 try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
//                 BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME + ".tmp"))) {
//             boolean found = false;
//             System.out.print("Enter staff ID to delete: ");
//             int idToDelete = Integer.parseInt(reader.readLine());
//             String line;
//             while ((line = reader.readLine()) != null) {
//             String[] parts = line.split(",");
//             int memberid = Integer.parseInt(parts[0]);
//             if (memberid == idToDelete) {
//             found = true;
//             System.out.println("Staff deleted successfully.");
//             } else {
//             writer.write(line + "\n");
//             }
//             }
//             if (!found) {
//             System.out.println("No staff found with ID " + idToDelete);
//             }
//             } catch (IOException e) {
//             System.err.println("Error deleting staff from file: " + e.getMessage());
//             }
//             // Replace original file with updated file
//             File file = new File(FILE_NAME);
//             file.delete();
//             new File(FILE_NAME + ".tmp").renameTo(file);
//	    }
	 
	 
//	 @Override
//		public void getAllMembers() {
//			// TODO Auto-generated method stub
//			int count = 0;
//			List<Member> bookListtoAvailabale = this.getBooksbyFile();
//			
//			for(Book book: bookListtoAvailabale) {
//				
//					System.out.println(count +1+ ". " + book.getBookName());
//					count++;
//					
//			}
//			
//		}

	 
	 @SuppressWarnings("finally")
	@Override
		public List<Member> getMemberAll() {
			// TODO Auto-generated method stub
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
	                Member member = new Member(memberId, firstName, lastName, dob, nic, phoneNumber, email, address);
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
					writer.write(member.getMemberId()+","+member.getFirstName()+","+member.getLastName()+","+member.getNic()+","+member.getDob()+","+tbEmail+","+tbPhoneNumber+","+ tbAddress+"\n" );
					
										
				}
				else {
				
					writer.write(member.getMemberId()+","+member.getFirstName()+","+ member.getLastName()+","+","+ member.getNic()+","+ member.getDob()+","+ member.getEmail()+","+ member.getPhoneNumber()+","+ member.getAddress()+ "\n" );
				}
			}
			
		
			
			System.out.println("\nMember Updated Successfully....\n");
			writer.close();
			
		}catch(IOException e){
			System.out.println("Writing error: " + e.getMessage());
			
		}
	}
}
		
	
	



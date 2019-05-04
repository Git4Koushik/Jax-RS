package com.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.bin.Address;
import com.test.bin.Student;

@Path("/Student")
public class StudentController {
	
	
	FileOutputStream fos;
	List<Student> studList=null;
	
	@GET
	@Path("/Students")
	public List<Student> getAllStudents() {
		
		File file = new File("D:\\Student_Base.txt");
		if(!file.exists()) {
			
			Student stud = new Student(1,"Koushik","Das",8,new Address(945,"S.H.K.Sarani",700074));
			studList = new ArrayList<Student>();
			studList.add(stud);
			saveIntoFile(studList);
			return studList;
			
		}else {
								
		    return getListFromFile();
		}
		
	}
	
	@POST
	@Path("/Create")
	@Produces(MediaType.APPLICATION_XML)   
	@Consumes(MediaType.APPLICATION_XML)
	public String saveStudent(Student student){
		
		try {
			List<Student> studList = getListFromFile();
			studList.add(student);
			saveIntoFile(studList);
			return "Success"; 
			
		}catch(Exception ex) {
			ex.printStackTrace();	
			return "Failed";
		}
	
	
	}
	
	private List<Student> getListFromFile() {
		File file = new File("D:\\Student_Base.txt");
		try {
			FileInputStream fos = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fos);
			studList = (List<Student>) ois.readObject();
			ois.close();
			}catch(Exception Ex){
				Ex.printStackTrace();
				return null;
			}
		
		    return studList;
		}
		
	
	
	private void saveIntoFile(List<Student> studList ){
	
		
		File file = new File("D:\\Student_Base.txt");
		
		try {	
				
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(studList);
			oos.close();
			fos.close();
		}catch(Exception Ex){
			Ex.printStackTrace();
		}
	}

}

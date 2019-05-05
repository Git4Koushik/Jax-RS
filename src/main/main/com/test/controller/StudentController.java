package com.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.bin.Address;
import com.test.bin.Student;

@Path("/Student")
public class StudentController {

	FileOutputStream fos;
	List<Student> studList = null;

	@GET
	@Path("/Students")
	public List<Student> getAllStudents() {

		File file = new File("D:\\Student_Base.txt");
		if (!file.exists()) {

			Student stud = new Student(1, "Koushik", "Das", 8, new Address(945, "S.H.K.Sarani", 700074));
			studList = new ArrayList<Student>();
			studList.add(stud);
			saveIntoFile(studList);
			return studList;

		} else {

			return getListFromFile();
		}

	}

	@POST
	@Path("/Create")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public String saveStudent(Student student) {

		try {
			studList = getListFromFile();
			for (Student stud : studList) {

				if (stud.getRoll() == student.getRoll()) {

					return ("Already this Roll exist");
				}

			}

			studList.add(student);
			saveIntoFile(studList);
			return "Success";

		} catch (Exception ex) {
			ex.printStackTrace();
			return "Failed";
		}

	}

	@PUT
	@Path("/Update")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public String updateStudent(Student student) {

		studList = getListFromFile();

		// ************************ Finding the student ******************************//
		for (Student stud : studList) {

			if (stud.getRoll() == student.getRoll()) {

				/// ************* Updating operation starts ****************************888//
				Class cls = student.getClass();
				Class cls1 = stud.getClass();

				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods)
					if (method.getName().matches("(.*)get(.*)()")) {
						try {
							Object val = method.invoke(student, null);
							if (!(val.equals("")) || (val != null) || ((Integer) val != 0)) {

								String setMethodeName = method.getName().replaceAll("get", "set");
								Class type = method.getReturnType();

								Method methodcall1 = cls.getDeclaredMethod(setMethodeName, type);
								methodcall1.invoke(stud, val);

								// studList.add(stud);
								saveIntoFile(studList);
								return ("Update sucessfull");
							}

						} catch (Exception e) {

						}

					}
				// *************************Updating operating  ends**************************************************//

			}

		}

		return ("No such student exist");

	}

	@DELETE
	@Path("/Delete/{id}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public String deleteStudent(@PathParam("id") int roll) {

		studList = getListFromFile();
		for (Student stud : studList) {
			if (stud.getRoll() == roll) {

				studList.remove(stud);
				saveIntoFile(studList);
				return "Deleted Sucessfully";
			}

		}

		return "No Such Roll Exist";
	}

	private List<Student> getListFromFile() {
		File file = new File("D:\\Student_Base.txt");
		try {
			FileInputStream fos = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fos);
			studList = (List<Student>) ois.readObject();
			ois.close();
		} catch (Exception Ex) {
			Ex.printStackTrace();
			return null;
		}

		return studList;
	}

	private void saveIntoFile(List<Student> studList) {

		File file = new File("D:\\Student_Base.txt");

		try {

			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(studList);
			oos.close();
			fos.close();
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

}

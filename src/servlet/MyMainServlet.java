package servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class MyMainServlet
 */
@WebServlet("/MyMainServlet")
public class MyMainServlet extends HttpServlet {

	public static String MY_FILE_FILE_RES="";
	public static String PREVIOUS_TRACER="";

	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyMainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub


	}

	/**
	 * @see HttpServlet#doPost (HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			/* report an error */ }

		try {
			JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
			System.out.println("jsonObject " + jsonObject);

			
			if(jsonObject.getString("Method").equalsIgnoreCase("5")) {
				response.getWriter().println(getDataFromDirectory());
				return;

				/* else block for installing app and lunch */
			} else if(jsonObject.getString("Method").equalsIgnoreCase("4")) {
		
				 String ls_str="";
				//  Process pq = Runtime.getRuntime().exec("cmd /c InstallyaantraQcExcutableFile.bat", null,
				  //Process pq = Runtime.getRuntime().exec("cmd /c testmy.bat", null,
				 Process pq = Runtime.getRuntime().exec("cmd /c FOR_TRC_APP_BATCH.bat", null,
						new File("E:\\Arun_Java_Workspace\\YaantraMobileDiagnosis\\platform-tools"));
				   DataInputStream	 ls = new DataInputStream(pq.getInputStream());
				
				   System.out.println("my data "+ ls.toString());
				
				   
				    while ((ls_str = ls.readLine()) != null) {
					System.out.println("MY LS "+ls_str);
					 
					if(ls_str.contains("adb: error: failed to get feature set: no devices/emulators found")) {
						  System.out.println("1rst condition"); 
						  MY_FILE_FILE_RES=ls_str;
						  break;
					  }else if(ls_str.contains("waiting for device")) {
						  System.out.println("2nd condition");   
						  MY_FILE_FILE_RES=ls_str;
						  break;
					  } else if(ls_str.contains("This adb server's $ADB_VENDOR_KEYS is not set")) {
						  System.out.println("3rd condition");   
						  MY_FILE_FILE_RES=ls_str;
						  break;		  
					  }
				}
				
				  System.out.println("MY_FILE_FILE_RES "+MY_FILE_FILE_RES);
				 if(MY_FILE_FILE_RES.contains("adb: error: failed to get feature set: no devices/emulators found")) {
					  System.out.println("1rst condition main"); 
					  MY_FILE_FILE_RES="";
					  response.getWriter().println("deviceNotConnected");
					  return;	  
				  }else if(MY_FILE_FILE_RES.contains("waiting for device")) {
					  System.out.println("2nd condition");  
					  MY_FILE_FILE_RES="";
					  response.getWriter().println("deviceNotConnected");
					  return;
				
				  }else if(MY_FILE_FILE_RES.contains("This adb server's $ADB_VENDOR_KEYS is not set")) {
					  System.out.println("3rd condition");  
					  MY_FILE_FILE_RES="";
					  response.getWriter().println("grantPermissoinToDubugging");
					  return;
				 
				  }else if(MY_FILE_FILE_RES.contains("This adb server's $ADB_VENDOR_KEYS is not set")) {
					  System.out.println("3rd condition");  
					  MY_FILE_FILE_RES="";
					  response.getWriter().println("grantPermissoinToDubugging");
					  return;
				  } else {
					        MY_FILE_FILE_RES="";
						    File fileq = new File("E:\\Arun_Java_Workspace\\YaantraMobileDiagnosis\\platform-tools\\mttext.txt");
						    fileq.createNewFile();
                  			FileWriter writer = null;
                  			writer = new FileWriter(fileq);
                  			writer.write("Start");
                  			writer.close(); 
					       response.getWriter().println("nextStep");
					  return; 
				  }

				/* below code is for let to user know seeing next step */
				
				
			}

		} catch (JSONException e) {
			 response.getWriter().println("Client is not ready...");
		}catch (Exception e) {
			 response.getWriter().println("Client is not ready...");
			  e.printStackTrace();
		}finally {
			
		}

	}

	public String getDataFromDirectory() throws IOException {
		
		 DataInputStream ls_in = null;
		 Process p = null;
		 BufferedReader br = null;
		 String st = null;
		 File file = null; 
		try {
			String ls_str;
			
			p = Runtime.getRuntime().exec("cmd /c getFileFromAndroid.bat", null,
			new File("E:\\Arun_Java_Workspace\\YaantraMobileDiagnosis\\platform-tools"));
			ls_in = new DataInputStream(p.getInputStream());

			while ((ls_str = ls_in.readLine()) != null) {
				System.out.println(ls_str);
			}

			 file = new File("E:\\Arun_Java_Workspace\\YaantraMobileDiagnosis\\platform-tools\\mttext.txt");
			 br = new BufferedReader(new FileReader(file));
			
			while ((st = br.readLine()) != null) {
				 if (!PREVIOUS_TRACER.equalsIgnoreCase(st)) {
					 System.out.println(st);
					 PREVIOUS_TRACER = st;
				      }
				 
				 if(PREVIOUS_TRACER.equalsIgnoreCase("qqqqqarunkhkDisconnected")||PREVIOUS_TRACER.equalsIgnoreCase("Disconnected")) {
					 PREVIOUS_TRACER="Disconnected";
					}	
				System.out.println(st);
			}

		} catch (Exception e) {
			System.out.println("Exception e: " + e);

		} finally {
			/*
			 * if(file.delete()){ System.out.
			 * println("File E:\\Arun_Java_Workspace\\YaantraMobileDiagnosis\\platform-tools\\mttext.txt File deleted"
			 * ); }else { System.out.
			 * println("File E:\\Arun_Java_Workspace\\YaantraMobileDiagnosis\\platform-tools\\mttext.txt doesn't exists"
			 * ); }
			 */
			ls_in.close();
			br.close();
			Runtime rt = Runtime.getRuntime();
			rt.exec("taskkill " + p);
		}

		return PREVIOUS_TRACER;
	}

}

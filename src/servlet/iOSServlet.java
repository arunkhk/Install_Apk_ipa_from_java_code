package servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import servlet.contant.AppConstant;
import servlet.util.AppUtil;

/**
 * Servlet implementation class iOSServlet
 */
@WebServlet("/iOSServlet")
public class iOSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String MY_FILE_FILE_RES="";
	public static String PREVIOUS_TRACER="";
	public static String PREVIOUS_TRACER_FOR_MT_TEXT="";
	Process pq;
	 DataInputStream ls;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iOSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObject = null ;
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		
		
		
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
			
			jsonObject = HTTP.toJSONObject(jb.toString());
			System.out.println("jsonObject " + jsonObject.toString());
			if(jsonObject.getString("Method").equalsIgnoreCase("6")) {
				try {   
					 String ls_str="";
					   pq = Runtime.getRuntime().exec("cmd /c install_ipa.bat", null,
					
							new File("E:\\Arun_Java_Workspace\\YaantraMobileDiagnosis\\iOS-installer"));
					         ls = new DataInputStream(pq.getInputStream());
					
					   System.out.println("my data "+ ls.toString());
					while ((ls_str = ls.readLine()) != null) {
						System.out.println("MY LS "+ls_str);
						
						if(ls_str.contains("Uninstall: Complete")) {
							  MY_FILE_FILE_RES="Data for uninstalling";
							  System.out.println("3rd condition "+MY_FILE_FILE_RES);   		 		
						  }else if(ls_str.contains("Install: Complete")) {
							  MY_FILE_FILE_RES=ls_str;
							  System.out.println("4th condition "+MY_FILE_FILE_RES);   		 
							  break;		
						  		
						  }
					}
					
					System.out.println("MY_FILE_FILE_RES "+MY_FILE_FILE_RES);
					
		
					if(MY_FILE_FILE_RES.equalsIgnoreCase("")) {
						  System.out.println("1rst condition main"); 
						  MY_FILE_FILE_RES="";
						  response.getWriter().println("deviceNotConnected");
						  return;
						  
					  }else {
						
						  
                    if(!MY_FILE_FILE_RES.contains("Data for uninstalling")) {
                    	  MY_FILE_FILE_RES="";
                    	  
                    	    File fileq = new File(AppConstant.IOS_DIR_WHERE_JAVA_APPICATION_GET_DATA);
						    fileq.createNewFile();
                			FileWriter writer = null;
                			writer = new FileWriter(fileq);
                			writer.write("Start");
                			writer.close(); 
                    	 response.getWriter().println("nextStep");
                     }
						 
						  return; 
					  }
		
			}catch (Exception e) {
				 response.getWriter().println("Client is not ready...");
				  e.printStackTrace();
			}finally {
				System.out.println(" closing operation"+MY_FILE_FILE_RES);
				ls.close();
				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill " + pq);
			}
			
				
			}else if(jsonObject.getString("Method").equalsIgnoreCase("7")) {
				response.getWriter().println(getDataFromDirectory());	
			}
		} catch (Exception e) {
			 response.getWriter().println("Client is not ready please wait...");}

	}
	
	public String getDataFromDirectory() throws Exception {		
		 DataInputStream ls_in = null;
		 Process p = null;
		 BufferedReader br = null;
		 String st = null;
		 File file = null; 
		try {
			  file = new File("E:\\Arun_Java_Workspace\\YaantraMobileDiagnosis\\Release\\getdata.txt"); 
			  br = new BufferedReader(new FileReader(file)); 
			 

				while ((st = br.readLine()) != null) {
					if (!PREVIOUS_TRACER.equalsIgnoreCase(st)) {
						PREVIOUS_TRACER = st;
						if(st.contains("qqqqqStart")) {
							AppUtil.createFile(st);
						}else if (st.contains("qqqFlLi_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqWifi_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqgGPS_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqBlth_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqVlUp_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqVlDo_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqMoNe_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqBaCa_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqFrCa_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqToDi_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqChJa_Ok")) {
							AppUtil.createFile(st);
						} else if (st.contains("qqqSpek_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqqMiph_Ok")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqFlLi_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqWifi_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqgGPS_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqBlth_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqVlUp_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqVlDo_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqMoNe_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqBaCa_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqFrCa_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqToDi_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqChJa_Not")) {
							AppUtil.createFile(st);
						} else if (st.contains("qqSpek_Not")) {
							
							AppUtil.createFile(st);
						} else if (st.contains("qqMiph_Not")) {
							AppUtil.createFile(st);
						} else if (st.contains("qqHelth_")) {
							AppUtil.createFile(st);
						} else if (st.contains("YANTRA_DIONOSIS_JAVA_PROJECT :Pr_")) {
							AppUtil.createFile(st);
						} else if (st.contains("qqqqqarunkhkDisconnected")) {
							AppUtil.createFile(st);
						}
					}
				}

				br.close();
				  file = new File(AppConstant.IOS_DIR_WHERE_JAVA_APPICATION_GET_DATA);
					// Create the file); 
				  br = new BufferedReader(new FileReader(file)); 
				  while ((st = br.readLine()) != null) {
					  if(!PREVIOUS_TRACER.equalsIgnoreCase(st)) { 
						  PREVIOUS_TRACER=st;
					  }	 
				  }
				  br.close();
		
		} catch (Exception e) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Client is not ready...";
			//AppUtil.createFile(PREVIOUS_TRACER_FOR_MT_TEXT);
			System.out.println("Exception e: " + e);
		}
		
		if(PREVIOUS_TRACER.equalsIgnoreCase("qqqqqStart")||PREVIOUS_TRACER.equalsIgnoreCase("Start")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Start";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqFlLi_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Flashlight_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqFlLi_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Flashlight_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqWifi_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Wifi_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqWifi_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Wifi_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqgGPS_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="GPS_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqgGPS_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="GPS_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqBlth_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Bluetooth_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqBlth_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Bluetooth_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqVlUp_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Volume+_Ok";
			
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqVlUp_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Volume+_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqVlDo_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Volume-_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqVlDo_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Volume-_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqMoNe_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Network_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqMoNe_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Network_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqBaCa_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="BackCamera_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqBaCa_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="BackCamera_Not";
		}
		else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqFrCa_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="FrontCamera_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqFrCa_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="FrontCamera_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqToDi_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Touch_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqToDi_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Touch_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqChJa_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Charger_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqChJa_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Charger_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqSpek_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Speaker_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqSpek_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Speaker_Not";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqqMiph_Ok")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Microphone_Ok";
		}else if(PREVIOUS_TRACER.equalsIgnoreCase("qqMiph_Not")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Microphone_Not";
		}
		else if(PREVIOUS_TRACER.contains("qqHelth_")) {
			String[] separated = PREVIOUS_TRACER.split("_");
			try {
				System.out.println(separated[0]); // this will contain "Health Score title"
				System.out.println(separated[1]);// this will contain "Health Score"
			}catch(Exception e) {
				PREVIOUS_TRACER_FOR_MT_TEXT="Health Score Got error_t";
			}
			
			PREVIOUS_TRACER_FOR_MT_TEXT="Health Score_"+separated[1];
		}else if(PREVIOUS_TRACER.contains("Pr_")) {
			
			String[] separated = PREVIOUS_TRACER.split("_");
			try {
				System.out.println(separated[0]); // this will contain "Health Score title"
				System.out.println(separated[1]);// this will contain "Health Score"
				System.out.println(separated[2]);
				PREVIOUS_TRACER_FOR_MT_TEXT="Price_"+separated[2]+"_"+separated[1];
			}catch(Exception e) {
				PREVIOUS_TRACER_FOR_MT_TEXT="price got error_t";
			}
		}else if(PREVIOUS_TRACER.contains("Disconnected")) {
			PREVIOUS_TRACER_FOR_MT_TEXT="Disconnected";
		}
		
		
		/*
		 * } else if (st.contains("qqHelth_")) { AppUtil.createFile(st); } else if
		 * (st.contains("YANTRA_DIONOSIS_JAVA_PROJECT :Pr_")) { AppUtil.createFile(st);
		 * }
		 */
		
		return PREVIOUS_TRACER_FOR_MT_TEXT;
	}

}

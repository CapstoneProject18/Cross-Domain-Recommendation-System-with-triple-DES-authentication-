
import java.util.*;
import java.io.*;
import static java.lang.System.in;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.runtime.Debug.id;

class TimeOutTask extends TimerTask {
	boolean isTimedOut = false;
	
	public void run() {
		isTimedOut = true;
	}
}

@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyServlet myClass = new MyServlet();
                          HttpSession session = request.getSession();

 
/*class OTPServer {
	public static void main(String args[]) throws IOException { 
		ServerSocket serverSocket = new ServerSocket(7777);
		System.out.println("Server running and waiting for client...");
		Socket clientSocket = serverSocket.accept()
        	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);*/
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// Server waits for a client to send its user ID
		//String id = in.readLine();
                String userid = request.getParameter("uname");
              //  String username = (String)request.getAttribute("un");
		TimeOutTask task = new TimeOutTask();
		Timer t = new Timer();
		t.schedule(task, 100000L);
		
                
		// Server generates an OTP and waits for client to send this
		Random r = new Random();
		String otp = new String();
		for(int i=0 ; i<8 ; i++) {
			otp += r.nextInt(10);
		}
                
		System.out.println(otp);
                
		// Server starts a timer of 10 seconds during which the OTP is valid.
//		String newId = in.readLine();
//		String newOtp = in.readLine();

//                if(newId.equals(id)) {
//			// User ID is verified
//			if(task.isTimedOut) {
//				// User took more than 100 seconds and hence the OTP is invalid
//				out.println("Time out!");
//			} else if(!newOtp.equals(otp)) {
//				out.println("Incorrect OTP!");
//			} else {
//				out.println("Logged In!");
//			}
//		
//
//		}
                //System.out.println("yessssss");
                
                
                session.setAttribute("uname", userid);
               // request.setAttribute("otpvalue", otp);
                request.getRequestDispatcher("success.jsp").forward(request, response);
		System.exit(0);
	}
}

/*

Output:
Server running and waiting for client...
28748125

*/
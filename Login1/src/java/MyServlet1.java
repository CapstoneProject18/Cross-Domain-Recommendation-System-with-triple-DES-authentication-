
import java.util.*;
import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myservlet1")
public class MyServlet1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
      

		System.out.println("Connecting to the server...");
		Socket clientSocket = new Socket("localhost", 7777);
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		//BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//		
//		 Client enters ID. This will be used by the program for verifying who
//		 is communicating as well as check the OTP against the ID, on the
//		 server side
//		System.out.println("Enter your ID:");
//		String id = scan.nextLine();
//		System.out.println("Contacting server...");
//		out.println(id);
//		System.out.println("Server has sent the OTP. Please enter it here:");
//		String otp = scan.nextLine();
//		System.out.println("Verifying...");
//		out.println(id);
//		out.println(otp);
//		System.out.println(in.readLine());

                String id = "";
                String newId = "";
                System.out.println("byeeee");
		String newOtp = request.getParameter("otp");
                String otp = request.getAttribute("otpvalue").toString();
                
                TimeOutTask task = new TimeOutTask();
		Timer t = new Timer();
		t.schedule(task, 100000L);

                if(newId.equals(id)) {
			// User ID is verified
			if(task.isTimedOut) {
				// User took more than 100 seconds and hence the OTP is invalid
				out.println("Time out!");
			} else if(!newOtp.equals(otp)) {
				out.println("Incorrect OTP!");
			} else {
				out.println("Logged In!");
			}
		

		}
		
                request.getRequestDispatcher("registration.jsp").forward(request, response);
		
		out.close();
		clientSocket.close();
	}
}

/*

Output:
Connecting to the server...
Enter your ID:
manav
Contacting server...
Server has sent the OTP. Please enter it here:
02489572
Verifying...
Logged In!

*/
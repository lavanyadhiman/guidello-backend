package guidello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class guidelloServlet
 */
public class guidelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Users users=new Users;
    /**
     * Default constructor. 
     */
    public guidelloServlet() {
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
		String name=request.getParameter("Name");
		String email=request.getParameter("Email");
		String password=request.getParameter("Password");
		String gender=request.getParameter("Gender");
		String p_no=request.getParameter("Phone number");
		String nationality=request.getParameter("Nationality");
		String languages=request.getParameter("Languages");
		String v_id=request.getParameter("Verification ID");
		users.addUsers().setName=(name);
		g.addUsers.
	}

}

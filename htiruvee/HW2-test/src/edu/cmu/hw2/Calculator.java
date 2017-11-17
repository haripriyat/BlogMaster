package edu.cmu.hw2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calculator")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String result;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		outputHtml(response,null,null,null,null);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		
		if(x == null && y == null) {
			result = "X cannot be null" + "\n & Y cannot be null" ;
			outputHtml(response,null,null,null,result);
			return;
		}
		
		else if (x == null)  {
			result = "X cannot be null";
			outputHtml(response,null,null,null,result);
			return;
		}
		
		else if (y == null)  {
			result = "Y cannot be null";
			outputHtml(response,null,null,null,result);
			return;
		}
		
		if(x.trim().equals("") && y.trim().equals("")) {
			result = "X cannot be empty" + "\n & Y cannot be empty";
			outputHtml(response,null,null,null,result);
			return;
		}
		
		else if(x.trim().equals("")) {
			result = "X cannot be empty";
			outputHtml(response,x,y,null,result);
			return;
		}
		
		else if(y.trim().equals("")) {
			result = "Y cannot be empty";
			outputHtml(response,x,y,null,result);
			return;
		}

		
		if(isNumber(x) == true && isNumber(y)==true) {
			
			double num1 = Double.parseDouble(x);
			double num2 = Double.parseDouble(y);
			DecimalFormat df = new DecimalFormat("#,###.00");
			
			if (request.getParameter("add")!=null) {
				outputHtml(response,x,y,"add",df.format(num1) + " + " + df.format(num2) + " = " + df.format(addTwoNums(num1,num2)));
			} 
			else if(request.getParameter("sub")!=null) {
				outputHtml(response,x,y,"sub",df.format(num1) + " - " + df.format(num2) + " = " + df.format(subTwoNums(num1,num2)));
			}
			else if(request.getParameter("div")!=null) {
				if(num2==0) {
					result = "Cannot Divide by 0";
					outputHtml(response,x,y,"div",result);
				}
				else {
					outputHtml(response,x,y,"div",df.format(num1) + " / " + df.format(num2) + " = " + df.format(divTwoNums(num1,num2)));
				}	
			}
			else if(request.getParameter("mul")!=null) {
				outputHtml(response,x,y,"mul",df.format(num1) + " * " + df.format(num2) + " = " + df.format(mulTwoNums(num1,num2)));
			}
		}
		
	
		else if(isNumber(x)==false && isNumber(y)==false) {
			result =  "X is not a Number " + "\n & Y is not a Number";	
			outputHtml(response,x,y,null,result);
		}
		
		else if(isNumber(x)==false) {
			result = "X is not a Number";
			outputHtml(response,x,y,null,result);
		}
		
		else if(isNumber(y)==false) {
			result = "Y is not a Number";
			outputHtml(response,x,y,null,result);
		}
		
		
	}	
	
	
	public static boolean isNumber(String num) {
	      boolean isValidNumber = false;
	      try
	      {
	         Double.parseDouble(num);
	         // s is a valid integer
	 
	         isValidNumber = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         return false;
	      }
	 
	      return isValidNumber;
	   }
	
	
	private Double addTwoNums(double x,double y) {
		Double ans = x + y;
		return ans;
	}
	
	private Double subTwoNums(double x,double y) {
		Double ans = x - y;
		return ans;
	}
	
	private Double divTwoNums(double x,double y) {
		Double ans = x / y;
		return ans;
	}
	
	private Double mulTwoNums(double x,double y) {
		Double ans = x * y;
		return ans;
	}
	
    private void outputHtml(HttpServletResponse response, String num1,String num2,String operator, String result)
			throws IOException {
    	
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Calculator</title>");
		out.println("<style>");
		out.println("     h2{");
		out.println("        color: green");
		out.println("       }");
		out.println("     img{");
		out.println("          width:45px");
		out.println("          height:50px");
		out.println("         }");
		out.println("      p{");
		out.println("          font-weight: bold ");
		out.println("         }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		if(result!=null) {
			out.println("<div>");
			out.println(result);
			out.println("   <hr size=\"2\" width=325 align=\"left\" noshade>");
			out.println("</div>");
		}
		out.println("<div>");
		out.println("   <h2>Simple Calculator</h2> ");
		out.println("  <form action=\"Calculator\" method=\"POST\">");
		out.println("    <p>");
		out.println("    	X: <input type=\"text\" value=\"" + ((num1!=null)?num1:new String("")) + "\" name=\"x\">");
		out.println("    	Y: <input type=\"text\" value=\"" + ((num2!=null)?num2:new String("")) + "\" name=\"y\">");
		out.println("    </p>");
		out.println("    <p> Choose the Arithmetic operator: </p>");
		out.println("    <input type=\"submit\" name=\"add\"  value=\"+\">");
		out.println("    <input type=\"submit\"  name=\"sub\" value=\"-\">");
		out.println("    <input type=\"submit\"  name=\"mul\" value=\"*\">");
		out.println("    <input type=\"submit\"  name=\"div\" value=\"/\">");
		out.println("  </form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
    
    private String streamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            StringBuilder b = new StringBuilder();

            String line = br.readLine();
            while (line != null) {
                b.append(line);
                b.append('\n');
                line = br.readLine();
            }

            return b.toString();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}

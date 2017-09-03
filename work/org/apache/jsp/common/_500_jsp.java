package org.apache.jsp.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class _500_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    Throwable exception = org.apache.jasper.runtime.JspRuntimeLibrary.getThrowable(request);
    if (exception != null) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("<html lang=\"en\">\r\n");
 
	String base = request.getContextPath(); 

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("\t<meta charset=\"utf-8\" />\r\n");
      out.write("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge,chrome=1\" >\r\n");
      out.write("\r\n");
      out.write("\t<title>500 - 系统内部错误</title>\r\n");
      out.write("\r\n");
      out.write("\t<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\r\n");
      out.write("\r\n");
      out.write("\t<meta content=\"\" name=\"description\" />\r\n");
      out.write("\r\n");
      out.write("\t<meta content=\"\" name=\"author\" />\r\n");
      out.write("\r\n");
      out.write("\t<!-- BEGIN GLOBAL MANDATORY STYLES -->\r\n");
      out.write("\r\n");
      out.write("\t<link href=\"");
      out.print(base);
      out.write("/mainface/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\r\n");
      out.write("\t<link href=\"");
      out.print(base);
      out.write("/mainface/css/style-metro.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\r\n");
      out.write("\t<link href=\"");
      out.print(base);
      out.write("/mainface/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\r\n");
      out.write("\t<link href=\"");
      out.print(base);
      out.write("/mainface/css/error.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<!-- END HEAD -->\r\n");
      out.write("\r\n");
      out.write("<!-- BEGIN BODY -->\r\n");
      out.write("\r\n");
      out.write("<body class=\"page-500-full-page\">\r\n");
      out.write("\r\n");

	Throwable ex = null;
	if (exception != null)
	{
		//ex.printStackTrace();
		ex = exception;
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"row-fluid\">\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"span12 page-500\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\" number\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t500\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\" details\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<h3>系统发生内部错误，请联系管理员</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t      <font color=\"#FF0000\">\r\n");
      out.write("\t\t\t\t\t");

					if(request.getAttribute("errorInfo") != null)
						out.println("<font color=red size='3px'>" + request.getAttribute("errorInfo") + "</font>");
					if(ex != null)
					{
						if(ex.getCause() instanceof ConstraintViolationException)
							out.println("<font color=red size='3px'>执行不成功，存在该数据的引用，如果要删除，请先删除涉及到的数据！</font>");
					}
					
      out.write("      \r\n");
      out.write("\t\t\t\t  </font>\r\n");
      out.write("\t\t\t\t  <br><br>\r\n");
      out.write("\t\t\t\t  <a class=\"btn blue disabled\" href=\"javascript:void(0)\" onclick=\"disp()\">查看详细错误</a>\r\n");
      out.write("\t\t\t\t  &nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t  <a class=\"btn green disabled\" href=\"");
      out.print(request.getHeader("Referer"));
      out.write("\" >返回</a>\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"div1\" style=\"display:none\">\r\n");
      out.write("\t\t<font color=\"red\">");
      out.print(ex.toString());
      out.write("</font>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tfunction disp(){\r\n");
      out.write("\t\t\tif(document.getElementById(\"div1\").style.display == \"none\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"div1\").style.display = \"\";\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"div1\").style.display = \"none\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\t\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("<!-- END BODY -->\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

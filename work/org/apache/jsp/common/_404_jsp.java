package org.apache.jsp.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _404_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
 
	String base = request.getContextPath(); 

      out.write("\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("\t<meta charset=\"utf-8\" />\r\n");
      out.write("\t\r\n");
      out.write("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge,chrome=1\" >\r\n");
      out.write("\r\n");
      out.write("\t<title>404-页面找不到</title>\r\n");
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
      out.write("/mainface/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\r\n");
      out.write("\t<!-- END GLOBAL MANDATORY STYLES -->\r\n");
      out.write("\r\n");
      out.write("\t<!-- BEGIN PAGE LEVEL STYLES -->\r\n");
      out.write("\r\n");
      out.write("\t<link href=\"");
      out.print(base);
      out.write("/mainface/css/error.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<!-- END HEAD -->\r\n");
      out.write("\r\n");
      out.write("<!-- BEGIN BODY -->\r\n");
      out.write("\r\n");
      out.write("<body class=\"page-404-full-page\">\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"row-fluid\">\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"span12 page-404\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"number\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t404\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"details\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<h3>抱歉, 找不到相关的页面.</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t在系统中找不到或不存在相关的页面<br />请联系系统管理员.<br />\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<!-- END BODY -->\r\n");
      out.write("\r\n");
      out.write("</html>");
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

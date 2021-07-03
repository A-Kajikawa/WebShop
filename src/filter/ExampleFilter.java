package filter;

import java.io.IOException;

import com.sun.org.apache.xalan.internal.xsltc.dom.Filter;

/**
 * Servlet Filter implementation class ExampleFilter
 */
@WebFilter("/*")
public class ExampleFilter implements Filter {

  private String encoding="UTF-8";
  /**
   * Default constructor.
   */
  public ExampleFilter() {
    
  }

  /**
   * @see Filter#destroy()
   */
  @Override
  public void destroy() {
    
  }

  /**
   * @see Filter#doFilter(ServletRequest, ServletResponse,
   *      FilterChain)
   */
  @Override
  public void doFilter(ServletRequest request,
      ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    // place your code here
    request.setCharacterEncoding(encoding);
    response.setContentType("text/html; charset="
        + encoding);
    // pass the request along the filter chain
    chain.doFilter(request, response);
  }

  /**
   * @see Filter#init(FilterConfig)
   */
  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    
  }

}

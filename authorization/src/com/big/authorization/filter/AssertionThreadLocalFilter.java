package com.big.authorization.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;

public final class AssertionThreadLocalFilter implements Filter {  
  
    public void init(final FilterConfig filterConfig) throws ServletException {  
        // nothing to do here  
    }  
  
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {  
        final HttpServletRequest request = (HttpServletRequest) servletRequest;  
        final HttpSession session = request.getSession(false);  
        final Assertion assertion = (Assertion) (session == null ? request.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) : session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION));  
  
        try {  
            AssertionHolder.setAssertion(assertion);  
            filterChain.doFilter(servletRequest, servletResponse);  
        } finally {  
            AssertionHolder.clear();  
        }  
    }  
  
    public void destroy() {  
        // nothing to do  
    } 
    
}
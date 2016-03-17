/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.controller.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matheus Oliveira
 */
public class FilterLogin implements Filter {

    private final String USUARIO = "usuarioLogado";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requisicao = (HttpServletRequest) request;
        HttpSession sessao = requisicao.getSession();
        if (sessao.getAttribute(USUARIO) == null) {
            if(requisicao.getRequestURI().contains("restrito") && requisicao.getRequestURI().contains("login.xhtml")) {
                chain.doFilter(request, response);
            }else if(requisicao.getRequestURI().contains("restrito")) {
                ((HttpServletResponse) response).sendRedirect(requisicao.getContextPath() + "/restrito/login/login.xhtml");
            }else{
                chain.doFilter(request, response);
            }
        }else{
            if(!requisicao.getRequestURI().contains("restrito")){
                ((HttpServletResponse) response).sendRedirect(requisicao.getContextPath() + "/restrito/index.xhtml");
            }else if(requisicao.getRequestURI().contains("login.xhtml")){
                ((HttpServletResponse) response).sendRedirect(requisicao.getContextPath() + "/restrito/index.xhtml");
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

}

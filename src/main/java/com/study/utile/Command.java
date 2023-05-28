package com.study.utile;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command 인터페이스
 */
public interface Command {

    /**
     * Execute.
     *
     * @param request  HttpServletRequest request
     * @param response HttpServletResponse response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     * CommandManager 가 실행시키는 함수
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;
}

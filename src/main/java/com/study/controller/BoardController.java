package com.study.controller;
import com.study.command.DeleteArticleCommand;
import com.study.command.ReadArticleCommand;
import com.study.command.WriteArticleCommand;
import com.study.utile.CommandManager;
import com.study.command.ReadListCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BoardController
 * 모든 요청을 받아서, 처리하는 controller
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "boardsController", value = "/boards/free/*")
public class BoardController extends HttpServlet {
    /**
     * commandManager   : CommandManager 를 싱글톤 패턴으로 생성해서 호출
     */
    private CommandManager commandManager = new CommandManager();
    /**
     * init
     * commandManager 에 command 를 등록하는 함수
     */
    @Override
    public void init(){
        commandManager.registerCommand("list", new ReadListCommand());
        commandManager.registerCommand("view", new ReadArticleCommand());
        commandManager.registerCommand("write", new WriteArticleCommand());
        commandManager.registerCommand("delete", new DeleteArticleCommand());
    }

    /**
     * @param request : HttpServletRequest
     * @param response : HttpServletResponse
     * 요청을 받아서 url 을 확인하고, 맞는 url 에 대한 command 를 실행
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String url = request.getRequestURI();

        if(url.startsWith("/boards/free/list")){
            commandManager.executeCommand("list", request, response);
        }
        else if(url.startsWith("/boards/free/view")){
            commandManager.executeCommand("view", request, response);
        }
        else if(url.startsWith("/boards/free/write")){
            commandManager.executeCommand("write", request, response);
        }
        else if(url.startsWith("/boards/free/delete")){
            commandManager.executeCommand("delete", request, response);
        }
    }
}

package com.study.utile;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CommandManager
 * Command 를 저장하고, 사용하는 객체
 */
public class CommandManager {
    private Map<String, Command> commandMap;

    /**
     * CommandManager
     * CommandManager 를 초기화
     */
    public CommandManager() {
        commandMap = new HashMap<>();
    }

    /**
     * registerCommand
     * @param commandName 등록할 command 이름
     * @param command     등록할 command
     * command 를 등록하는 함수
     */
    public void registerCommand(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    /**
     * Execute command.
     *
     * @param commandName 사용할 command 이름
     * @param request     command 에 넘겨줄 request
     * @param response    command 에 넘겨줄 response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     * command 이름을 받아서 실행하고, 만약 command 가 등록이 되지 않았을 경우 pass 한다
     */
    public void executeCommand(String commandName, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Command command = commandMap.get(commandName);
        if (command != null) {
            System.out.println("Do " + commandName);
            command.execute(request, response);
        } else {
            System.out.println("해당 커맨드를 찾을 수 없습니다: " + commandName);
        }
    }
}

package com.study.command;

import static com.study.utile.CheckInput.getFileName;

import com.study.utile.Command;
import com.sun.tools.javac.comp.Todo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileCommand implements Command {

    /**
     * @TODO 저장할 파일 이름 만드는 UTIL 필요, 파일 저장 구현
     * @param request  HttpServletRequest request
     * @param response HttpServletResponse response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
//        Part filePart = request.getPart("file");
//        String fileName = getFileName(filePart);
//        System.out.println(fileName);
//        String applicationPath = request.getServletContext().getRealPath("");
//        String uploadFilePath = applicationPath + File.separator + "uploads";
//        System.out.println(applicationPath + File.separator + "uploads");
//        File fileSaveDir = new File(uploadFilePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdirs();
//        }
//        if (!fileName.isEmpty()) {
//            filePart.write(uploadFilePath + File.separator + fileName);
//        }

    }
}

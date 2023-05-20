package com.study.controller;

import static com.study.utile.CheckInput.isLong;

import com.study.dao.FileDao;
import com.study.dto.FileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "download", value = "/download/*")
public class Download extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // 경로 확인
        String seq = request.getPathInfo().substring(1);
        if (!isLong(seq)) {
            // 에러 발생
            return;
        }
        Long id = Long.parseLong(seq);
        //
        FileDao fileDao = FileDao.getInstance();
        FileDTO file = fileDao.getFileById(id);
        InputStream fileData = file.getFile();
        String fileName = file.getFileName();
        if (fileData != null) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            OutputStream out = response.getOutputStream();

            try (BufferedInputStream bis = new BufferedInputStream(fileData);
                BufferedOutputStream bos = new BufferedOutputStream(out)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}

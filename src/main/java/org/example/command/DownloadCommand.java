package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;

import java.io.IOException;
import java.io.InputStream;

public class DownloadCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Content-Disposition", "attachment; filename=\"filename.txt\"");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (InputStream stream = DownloadCommand.class.getClassLoader().getResourceAsStream("first.json")) {
            if (stream == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            byte[] data = stream.readAllBytes();
            resp.getOutputStream().write(data);
        } catch (IOException e) {
            throw new RuntimeException("Error during file download", e);
        }
    }
}

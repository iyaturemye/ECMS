/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Godwin
 */
public class FileUpload {

    public String Upload(FileUploadEvent event, String path) {
        try {
            String format = event.getFile().getContentType();
            String extension = (format.equalsIgnoreCase("image/jpeg") ? ".jpg" : (format.equalsIgnoreCase("image/png")) ? ".png" : format.equalsIgnoreCase("image/gif") ? ".gif" : format.equalsIgnoreCase("application/pdf") ? ".pdf" : "");
            String newName = UUID.randomUUID().toString() + extension;
            String pathToSave = FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/");
            path = pathToSave + path;
            copyFile(newName, event.getFile().getInputstream(), path);
            return newName;
        } catch (IOException e) {
            e.printStackTrace();
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getMessage());
            return null;
        }
    }

    private void copyFile(String fileName, InputStream in, String concatinationPath) {
        try {
            OutputStream out = new FileOutputStream(new File(concatinationPath + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

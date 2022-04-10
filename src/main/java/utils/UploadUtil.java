package utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadUtil {
    public static String uploadImage(String imagePart, HttpServletRequest request) throws ServletException, IOException {
        String uploadFolder = request.getServletContext().getRealPath("/upload"); //Cac file upload len se duoc luu tai day
        Path uploadPath = Paths.get(uploadFolder); //Tra ve duong dan cua folderUpload

        if (!Files.exists(uploadPath)) { //Tao file neu chua co
            Files.createDirectory(uploadPath);
        }

        Part imgPart = request.getPart(imagePart);
        if (imgPart.getSize() == 0 || imgPart == null) {
            return "no-image.jpg";
        }
        String imgFileName = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
        //Ghi du lieu duoc upload
        imgPart.write(Paths.get(uploadPath.toString(), imgFileName).toString());
        request.setAttribute("img", imgFileName);
        return imgFileName;
    }
}

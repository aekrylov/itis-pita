package ru.kpfu.itis.pita.misc;

import com.cloudinary.Cloudinary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.security.UserDetails;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/15/17 2:09 PM
 */
public class Helpers {

    public static <T> List<T> filterByType(Collection<? super T> collection, Class<T> type) {
        return collection.stream()
                .filter(item -> type.isAssignableFrom(item.getClass()))
                .map(item -> (T)item)
                .collect(Collectors.toList());
    }

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof UserDetails)) {
            return null;
        }

        return ((UserDetails) principal).getUser();
    }

    /**
     * Upload multipart file to Cloudinary
     *
     * Note that CLOUDINARY_URL system variable or property must exists for Cloudinary to work correctly
     * @param file file to upload
     * @return URL of uploaded file, or null if IO error is thrown by MultipartImage
     */
    public static String uploadFile(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary();
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), null);
            return (String) result.get("url");
        } catch (IOException e) {
            e.printStackTrace();
            //todo
            return null;
        }
    }
}

package net.itorbit.pesaram.filemanager.env;

import org.springframework.core.io.FileSystemResource;

/**
 * This class holds upload directory path.
 * @Linux /uploads in filemanager container
 * @Windows [Project Root]/uploads
 * @author <a href="mailto:sh.damghanpour@itorbit.net">MrSha1</a>
 */
public final class Environments {
    public static final String UPLOAD_DIRECTORY =
           (new FileSystemResource("").getFile().getAbsolutePath() + "/uploads").replaceAll("\\\\", "/").replaceAll("//", "/");

}

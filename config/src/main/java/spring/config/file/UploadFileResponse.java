package spring.config.file;

import lombok.Data;

@Data
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String fileRealName;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size,String fileRealName) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.fileRealName =fileRealName;

    }

}

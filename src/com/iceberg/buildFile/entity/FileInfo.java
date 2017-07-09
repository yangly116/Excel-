package com.iceberg.buildFile.entity;
/** 
 * 
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：2016年11月20日 下午2:53:49  
 */
/**
 * @author ywp
 *
 */
public class FileInfo {
	/**文件Id*/
	private Integer fileId;
	/**文件名*/
	private String fileName;
	/**文件路径*/
	private String filePath;
	/**文件上传时间*/
	private String uploadTime;
	/**文件修改时间*/
	private String alterTime;
	/**文件状态*/
	private String fileState;
	/**文件类型*/
	private String fileType;
	private String fileContent;
	private String userAgent;
	private String rootPath;
	/**节点的路径*/
	private String treeNoPath;
	/**新文件名*/
	private String newTreeNoName;
	
	public String getNewTreeNoName() {
		return newTreeNoName;
	}
	public void setNewTreeNoName(String newTreeNoName) {
		this.newTreeNoName = newTreeNoName;
	}
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	public String getTreeNoPath() {
		return treeNoPath;
	}
	public void setTreeNoPath(String treeNoPath) {
		this.treeNoPath = treeNoPath;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getAlterTime() {
		return alterTime;
	}
	public void setAlterTime(String alterTime) {
		this.alterTime = alterTime;
	}
	public String getFileState() {
		return fileState;
	}
	public void setFileState(String fileState) {
		this.fileState = fileState;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public FileInfo() {
		super();
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}

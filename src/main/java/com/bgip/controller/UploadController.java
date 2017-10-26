package com.bgip.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import com.bgip.exception.BgipException;
import com.bgip.filter.AuthFilter;
import com.bgip.model.upload.FolderResponse;
import com.bgip.model.ResponseBean;
import com.bgip.service.UploadServices;
import com.bgip.model.upload.FilesBean;
import com.bgip.model.upload.FolderBean;
import com.bgip.model.upload.FolderRequest;
import javax.ws.rs.core.Response;

@Path("/drive")
@Produces(APPLICATION_JSON)
public class UploadController extends BaseController {

	@Autowired
	UploadServices uploadServices;
	
	
	private AuthFilter authFilter;
	
	@GET
	@Path("/test")
	public String testA() {
		return "Welcome to Muthahar ";
	}
	
	
	@POST
	@Path("/upload")
	@Consumes(APPLICATION_JSON)
	public FolderRequest upload(@HeaderParam("token") String token, FolderRequest uploadFolder) throws Exception {
		FolderRequest result = null;
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		 try {
			 result = uploadServices.uploadFolder(uploadFolder, loginUser);
		 }catch (BgipException fe) {
				buildErrorResponse(Response.Status.PRECONDITION_FAILED, fe.getErrorCode(), fe.getMessage());
		 }
		
		
		return result;
	}
	
	
	
	@POST
	@Path("/newFolder")
	@Consumes(APPLICATION_JSON)
	public FolderBean createNewFolder(@HeaderParam("token") String token, FolderBean emptyFolder) throws Exception {
		FolderBean result = null;
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		 try {
			 result = uploadServices.createEmptyFolder(emptyFolder, loginUser);
		 }catch (BgipException fe) {
				buildErrorResponse(Response.Status.PRECONDITION_FAILED, fe.getErrorCode(), fe.getMessage());
		 }
		return result;
	}
	
	
	@GET
	@Path("/folder/{folderId}")
	@Produces(APPLICATION_JSON)
	public FolderResponse getFilesByFolderId(@HeaderParam("token") String token, @PathParam("folderId") String folderId) throws Exception {
		FolderResponse result = null;
		System.out.println(folderId);
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		 try {
			 result = uploadServices.getFolderDetails(folderId, loginUser);
		 }catch (BgipException fe) {
				buildErrorResponse(Response.Status.PRECONDITION_FAILED, fe.getErrorCode(), fe.getMessage());
		 }
		return result;
	}
	
//	@GET
//	@Path("/file/{fileId}")
//	@Produces(APPLICATION_JSON)
//	public FilesBean getFilebyId(@HeaderParam("token") String token, @PathParam("fileId") String fileId) throws Exception {
//		FilesBean result = null;
//		this.authFilter = new AuthFilter();
//		 String loginUser = this.authFilter.getUserNameFromToken(token);
//		 result = uploadServices.getFilbyId(fileId, loginUser);
//		
//		return result;
//	}
	
	
	
	@GET
//	@Path("/{userName}")
	@Produces(APPLICATION_JSON)
	public FolderResponse getAllFiles(@HeaderParam("token") String token ) throws Exception {
		FolderResponse result = null;
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		 result = uploadServices.getAllFiles(loginUser);
		return result;
	}
	
	
	@PUT
	@Path("/folder/favourite/{folderId}")
	@Produces(APPLICATION_JSON)
	public ResponseBean makeFavouriteFolder(@HeaderParam("token") String token, @PathParam("folderId") String folderId ) throws Exception {
		ResponseBean result = null;
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		 try {
			result = uploadServices.makeFavouriteFolder(folderId, loginUser);
		} catch (BgipException fe) {
			buildErrorResponse(Response.Status.CONFLICT, fe.getErrorCode(),
					fe.getMessage());
		}
		return result;
	}
	
	@PUT
	@Path("/file/favourite/{fileId}")
	@Produces(APPLICATION_JSON)
	public ResponseBean getFavouriteFileList(@HeaderParam("token") String token, @PathParam("fileId") String fileId ) throws Exception {
		ResponseBean result = null;
		System.out.println("files List 1: "+System.currentTimeMillis());
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		 try {
			 result = uploadServices.makeFavouriteFile(fileId, loginUser);
			} catch (BgipException fe) {
				buildErrorResponse(Response.Status.CONFLICT, fe.getErrorCode(),
						fe.getMessage());
			}
		return result;
	}
	
	@GET
	@Path("/favorite")
	@Produces(APPLICATION_JSON)
	public FolderResponse getFavouriteFolders(@HeaderParam("token") String token ) throws Exception {
		FolderResponse result = null;
		System.out.println("folder List 1: "+System.currentTimeMillis());
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		 result = uploadServices.getFavouriteFolders(loginUser);
		return result;
	}
	
	@GET
	@Path("/file/favourite/list")
	@Produces(APPLICATION_JSON)
	public List<FilesBean> getFavouriteFiles(@HeaderParam("token") String token ) throws Exception {
		List<FilesBean> result = null;
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		 result = uploadServices.getFavouriteFiles(loginUser);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

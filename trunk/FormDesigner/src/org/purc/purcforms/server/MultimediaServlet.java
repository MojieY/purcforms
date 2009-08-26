package org.purc.purcforms.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 
 * @author daniel
 *
 */
public class MultimediaServlet extends HttpServlet {

	private static final long serialVersionUID = 1239820102030344L;

	//private Log log = LogFactory.getLog(this.getClass());

	private byte[] postData = null;
	private String postContentType;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*if("recentbinary".equals(request.getParameter("action"))){
			if(postData != null){				
				response.setContentType(postContentType);
				response.getOutputStream().write(postData);
				postData = null;
				postContentType = null;
			}
			return;
		}

		try{
			String sFormId = request.getParameter("formId");
			String xpath = request.getParameter("xpath");
			String contentType = request.getParameter("contentType");
			String name = request.getParameter("name");
			
			if(name == null || name.trim().length() == 0)
				name = "multimedia.3gp";
				
			if(sFormId == null || sFormId.trim().length() == 0)
				return;

			if(xpath == null || xpath.trim().length() == 0)
				return;

			int id = Integer.parseInt(sFormId);
			FormData formData = Context.getStudyManagerService().getFormData(id);
			if(formData == null)
				return;

			String xml = formData.getData();
			if(xml == null || xml.trim().length() == 0)
				return;

			Document doc = XmlUtil.fromString2Doc(xml);
			if(doc == null)
				return;

			String value = XmlUtil.getNodeValue(doc, xpath);
			if(value != null && value.trim().length() > 0){
				byte[] bytes = Base64.decode(value);
				if(bytes != null){
					response.setHeader("Cache-Control", "no-cache");
			        response.setHeader("Pragma", "no-cache");
			        response.setDateHeader("Expires", -1);
			        response.setHeader("Cache-Control", "no-store");
			        
					if(contentType != null && contentType.trim().length() > 0){
						response.setContentType(contentType);
						
						//Send it as an attachement such that atleast firefox can also detect it
						if(contentType.contains("video") || contentType.contains("audio"))
							response.setHeader(OmevacConstants.HTTP_HEADER_CONTENT_DISPOSITION, OmevacConstants.HTTP_HEADER_CONTENT_DISPOSITION_VALUE + name + "\"");
					}
					
					response.getOutputStream().write(bytes);
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*postData = null;
		postContentType = null;

		CommonsMultipartResolver multipartResover = new CommonsMultipartResolver();
		if(multipartResover.isMultipart(request)){
			MultipartHttpServletRequest multipartRequest = multipartResover.resolveMultipart(request);
			MultipartFile uploadedFile = multipartRequest.getFile("filecontents");
			if (uploadedFile != null && !uploadedFile.isEmpty()) {
				postContentType = uploadedFile.getContentType();
				postData = uploadedFile.getBytes();
				response.getOutputStream().print(Base64.encode(postData));
			}
		}*/
	}
}

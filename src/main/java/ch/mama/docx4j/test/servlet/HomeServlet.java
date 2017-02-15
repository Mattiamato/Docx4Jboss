package ch.mama.docx4j.test.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.docx4j.openpackaging.io3.Save;
import org.docx4j.wml.P;

import ch.mama.docx4j.test.msoffice.docx4j.DocumentFactory;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(name="HomeServlet", urlPatterns={"*.do"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HomeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			byte[] res = test();
			
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			response.getOutputStream().write(res);
			response.setHeader("content-disposition", "attachment; filename=\"test.docx\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private byte[] test() throws Exception {

		ByteArrayOutputStream baos = null;
		InputStream is = null;
		BufferedInputStream bis = null;
		try {

			is = getClass().getResourceAsStream("template.docx");
			bis = new BufferedInputStream(is);
			DocumentFactory doc = new DocumentFactory(bis);

			P paragraph = doc.createParagraphOfText("This is a text in a paragraph.");
			doc.getMainDocumentPart().addObject(paragraph);
			
			baos = new ByteArrayOutputStream();
			Save saver = new Save(doc.getDocument());
			saver.save(baos);
			return baos.toByteArray();
		} catch (Exception e) {
			//
		} finally {
			if (baos != null){
				baos.close();
			}
			if (bis != null){
				bis.close();
			}
			if (is!= null){
				is.close();
			}
		}
		return null;
	}

}

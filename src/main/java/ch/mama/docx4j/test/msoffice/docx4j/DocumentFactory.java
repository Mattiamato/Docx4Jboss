/* *****************************************************************************
 * CREALOGIX AG 
 * Defense 
 * Baslerstrasse 60, Postfach 112 
 * CH-8066 Z�rich 
 * 
 * http://defense.crealogix.com
 *******************************************************************************
 *
 * $Author: Mattia Amato$
 * $Date: 09.02.2017 11:23:30$
 * $Revision: 1$
 *
 * $Project: Bund-FIS_LW$
 * $Folder: docx4j$
 * $Workfile: DocumentFactory.java$
 *
 * Description: see JavaDoc comment of this class.
 *
 ***************************************************************************** */

package ch.mama.docx4j.test.msoffice.docx4j;

import java.io.File;
import java.io.InputStream;

import org.docx4j.Docx4J;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.P;

/**
 * Word document factory.
 * 
 * @author mama
 * @version $Revision 0$
 * 
 *          <!--$NoKeywords$-->
 */
public class DocumentFactory {

	private final WordprocessingMLPackage document;

	private org.docx4j.wml.ObjectFactory of1 = Context.getWmlObjectFactory();

	/**
	 * Starts a new document
	 */
	public DocumentFactory() {
		this(new WordprocessingMLPackage());
	}

	public DocumentFactory(final WordprocessingMLPackage document) {
		super();
		this.document = document;
	}

	/**
	 * Reads the document from a byte stream. The procedure may require a lot of
	 * memory because the entire stream has to be read in.
	 * 
	 * @param in
	 *            Byte stream containing the document.
	 * @throws Docx4JException
	 *             if something wrong reading the stream.
	 */
	public DocumentFactory(final InputStream in) throws Exception {
		this(WordprocessingMLPackage.load(in));
	}

	/**
	 * Reads the document from a file or creates a new one if it doesn't exist.
	 * 
	 * @param file
	 *            The file containing the document
	 * @throws Docx4JException
	 *             if something wrong reading the file.
	 */
	public DocumentFactory(final File file) throws Exception {
		this(WordprocessingMLPackage.load(file));
	}

	public MainDocumentPart getMainDocumentPart() {
		return document.getMainDocumentPart();
	}

	public void save(File file) throws Exception {
		Docx4J.save(document, file);
	}

	public P createParagraphOfText(final String text) {
		return document.getMainDocumentPart().createParagraphOfText(text);
	}

	public P createStyledParagraphOfText(String styleName, final String text) {
		return document.getMainDocumentPart().createStyledParagraphOfText(styleName, text);
	}

	public OpcPackage getDocument() {
		return document.getPackage();
	}
}

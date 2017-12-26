package utils;

import java.io.File;
import java.util.Locale;

import org.springframework.web.servlet.view.InternalResourceView;

public class HtmlResourceView extends InternalResourceView {

	@Override
	public boolean checkResource(Locale locale) throws Exception {
		String absPath = this.getServletContext().getRealPath("/");
		System.out.println("absPath=" + absPath);
		System.out.println("getUrl=" + getUrl());
		File file = new File(absPath.substring(0, absPath.length() - 1)
				+ getUrl());
		return file.exists();
	}

}

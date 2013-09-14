package si.fri.iskalnikOseb.dataHelpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.bcel.generic.GETSTATIC;
import org.apache.commons.logging.LogFactory;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

/**
 * Razred za luscenje podatkov iz TISa
 * 
 * @author E520
 * 
 */
public class TisData {

	/**
	 * Vrne rezultate iskanja
	 */
	public static TisStructure[] submittingForm(String name) throws Exception {

		/**final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);*/
		WebClient webClient = getSilentClient(); 
		// Posljemo zahtevo za osnovno stran
		final HtmlPage page = webClient.getPage("http://www.itis.si/");

		// Posljemo zahteve za pridobitev ustreznih html elementov
		final HtmlForm form = page.getFormByName("aspnetForm");
		final HtmlSubmitInput button = form
				.getInputByName("ctl00$CPH_bodyMain$loginSearch$btnSearchPhoneBook");
		final HtmlTextInput textFieldIme = form
				.getInputByName("ctl00$CPH_bodyMain$loginSearch$tbSearchPhoneBookWhoOrWhat");
		textFieldIme.setValueAttribute(name);

		final HtmlTextInput textFieldKraj = form
				.getInputByName("ctl00$CPH_bodyMain$loginSearch$tbSearchPhoneBookWhere");
		textFieldKraj.setValueAttribute("");

		HtmlPage pageIskanje = button.click();
		// zdaj moramo klikniti "osebe"
		webClient.getOptions().setJavaScriptEnabled(true);
		pageIskanje = (HtmlPage) pageIskanje.refresh();

		final List<HtmlElement> divs1 = (List<HtmlElement>) pageIskanje
				.getByXPath("//a[@onclick=\"ResultsFilter.apply(\'PravniStatus\',\'OSEBE\');\"]");
		final HtmlPage pageOsebe = divs1.get(0).click();
		webClient.waitForBackgroundJavaScript(10000);

		List<HtmlElement> imenaOseb = (List<HtmlElement>) pageOsebe
				.getByXPath("//div[@class=\"r-in\"]//h3//a");
		List<HtmlElement> ulicaOseb = (List<HtmlElement>) pageOsebe
				.getByXPath("//div[@class=\"r-in\"]//p[@class=\"address\"]//a//span[@class=\"street\"]");
		List<HtmlElement> postaOseb = (List<HtmlElement>) pageOsebe
				.getByXPath("//div[@class=\"r-in\"]//p[@class=\"address\"]//a//span[@class=\"zip\"]");
		List<HtmlElement> telOseb = (List<HtmlElement>) pageOsebe
				.getByXPath("//div[@class=\"r-in\"]//p//span[@class=\"phone\" or @class=\"gsm\"]//a");

		// ce oseba ne obstaja
		if (imenaOseb.size() == 0)
			return null;
		TisStructure[] vseOsebe = new TisStructure[imenaOseb.size()];
		for (int i = 0; i < imenaOseb.size(); i++) {
			vseOsebe[i] = new TisStructure(imenaOseb.get(i).getTextContent(),
					telOseb.get(i).getTextContent(), ulicaOseb.get(i)
							.getTextContent(), postaOseb.get(i)
							.getTextContent());
		}
		return vseOsebe;

	}

	/**
	 * private static boolean aliSeImeUjema(String iskanoIme, String
	 * najdenoIme){ String[] razdeli = iskanoIme.split(" "); String[] razdeli1 =
	 * najdenoIme.split(" ");
	 * 
	 * 
	 * return false; }
	 */

	/**
	 * 
	 * @return Firefox_10 client that doesn't print CSS and javascript errors
	 * HtmlUnit library is very strict and prints 
	 */
	public static WebClient getSilentClient() {
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");

		Logger.getLogger("com.gargoylesoftware.htmlunit")
				.setLevel(Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient")
				.setLevel(Level.OFF);

		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		webClient.setIncorrectnessListener(new IncorrectnessListener() {
			
			@Override
			public void notify(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
		});
			
		webClient.setCssErrorHandler(new ErrorHandler() {
			
			@Override
			public void warning(CSSParseException arg0) throws CSSException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void fatalError(CSSParseException arg0) throws CSSException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void error(CSSParseException arg0) throws CSSException {
				// TODO Auto-generated method stub
				
			}
		});

		webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {

			@Override
			public void timeoutError(HtmlPage arg0, long arg1, long arg2) {
				// TODO Auto-generated method stub

			}


			@Override
			public void malformedScriptURL(HtmlPage arg0, String arg1,
					MalformedURLException arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void scriptException(HtmlPage arg0, ScriptException arg1) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void loadScriptError(HtmlPage arg0, URL arg1, Exception arg2) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		webClient.setHTMLParserListener(new HTMLParserListener() {

			@Override
			public void error(String arg0, URL arg1, String arg2, int arg3,
					int arg4, String arg5) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void warning(String arg0, URL arg1, String arg2, int arg3,
					int arg4, String arg5) {
				// TODO Auto-generated method stub
				
			}


		});

		return webClient;
	}

}

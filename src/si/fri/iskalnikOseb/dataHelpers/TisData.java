package si.fri.iskalnikOseb.dataHelpers;

import java.util.List;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

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

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		webClient.getOptions().setJavaScriptEnabled(false);

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
	private static boolean aliSeImeUjema(String iskanoIme, String najdenoIme){
		String[] razdeli = iskanoIme.split(" ");
		String[] razdeli1 = najdenoIme.split(" ");
		
		
		return false;
	}
	*/

}

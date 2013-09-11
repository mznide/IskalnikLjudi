package si.fri.iskalnikOseb.dataHelpers;

import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

//dela ampak treba se podatke  prave izsluzscit
public class SicrisData {

	public static SicrisStructure[] getSicrisData(String name)
			throws Exception {
		final WebClient webClient = new WebClient();
		// Get the first page
		final HtmlPage page1 = webClient.getPage("http://www.sicris.si");
		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		final HtmlForm form = page1.getFormByName("simple_search");
		final HtmlTextInput textField = form.getInputByName("search_term");
		// Change the value of the text field
		textField.setValueAttribute(name);
		final List<HtmlElement> divs = (List<HtmlElement>) form
				.getByXPath("//img[@src='/slv/images/buttons/poisci.gif']");
		final HtmlPage page2 = divs.get(0).click();

		/*
		 * tukaj so mo�ne 3 razli�ne stvari: -ni zadetkov -en zadetek (stran te
		 * preusmeri direktno na tega raziskovalca) -ve� zadetkov
		 */

		// -ce ni rezultatov
		List<HtmlElement> niZadetkov = (List<HtmlElement>) page2
				.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[2]/tbody/tr/td/p/b");
		if (niZadetkov.size() != 0) {
			if (niZadetkov.get(0).getTextContent()
					.equals("Ni zapisov, ki bi ustrezali podanim kriterijem.")) {
				return null;

			}

		}

		// ce ta element obstaja imamo vec raziskovalcev
		// List<HtmlElement> stRaziskovalcev = (List<HtmlElement>)
		// page2.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/div[2]/table/tbody/tr[1]/td/a");
		List<HtmlElement> stRaziskovalcev = (List<HtmlElement>) page2
				.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[2]/tbody/tr[2]/td/table/tbody/tr/td/a");
		// pomeni da smo ze na strani raziskovalca,
		if (stRaziskovalcev.size() == 0) {
			SicrisStructure[] osebe = new SicrisStructure[1];
			osebe[0] = izsluzsiEnZadetek(page2);
			return osebe;
		}

		// dobimo stran s seznamom raziskovalcev:
		// HtmlPage page3 = stRaziskovalcev.get(0).click();

		// List<HtmlElement> povezaveRaziskovalcev = (List<HtmlElement>)
		// page3.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[2]/tbody/tr[2]/td/table/tbody/tr/td[2]/a");

		SicrisStructure[] osebe = new SicrisStructure[stRaziskovalcev.size()];
		for (int i = 0; i < stRaziskovalcev.size(); i++) {
			HtmlPage pageOseba = stRaziskovalcev.get(i).click();
			osebe[i] = izsluzsiEnZadetek(pageOseba);

		}

		return osebe;
	}

	private static SicrisStructure izsluzsiEnZadetek(HtmlPage pageOseba) {
		if (pageOseba
				.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[3]/tbody/tr[2]/td/table/tbody/tr[1]/td[2]") != null) {

			String name = "";
			String phoneNumber = "";
			String faxNumber = "";
			String emailAddress = "";
			String website = "";

			List<HtmlElement> nameElement = (List<HtmlElement>) pageOseba
					.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[1]/tbody/tr[2]/td/table/tbody/tr[1]/td[2]");
			if (nameElement.size() != 0)
				name = nameElement.get(0).getTextContent();

			List<HtmlElement> phoneElement = (List<HtmlElement>) pageOseba
					.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[3]/tbody/tr[2]/td/table/tbody/tr[1]/td[2]");
			if (phoneElement.size() != 0)
				phoneNumber = phoneElement.get(0).getTextContent();

			List<HtmlElement> faxElement = (List<HtmlElement>) pageOseba
					.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[3]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]");
			if (faxElement.size() != 0)
				faxNumber = faxElement.get(0).getTextContent();

			List<HtmlElement> emailElement = (List<HtmlElement>) pageOseba
					.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[3]/tbody/tr[2]/td/table/tbody/tr[3]/td[2]");
			if (emailElement.size() != 0)
				emailAddress = emailElement.get(0).getTextContent();

			List<HtmlElement> websiteElement = (List<HtmlElement>) pageOseba
					.getByXPath("/html/body/table[4]/tbody/tr[1]/td[2]/table[3]/tbody/tr[2]/td/table/tbody/tr[4]/td[2]");
			if (websiteElement.size() != 0)
				website = websiteElement.get(0).getTextContent();
			return new SicrisStructure(name, phoneNumber, faxNumber,
					emailAddress, website);
		} else
			return new SicrisStructure("", "", "", "", "");

	}

	public static boolean aliJeRaziskovalec(String name) {
		return false;
	}

}

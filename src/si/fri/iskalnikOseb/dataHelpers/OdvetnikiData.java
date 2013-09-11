package si.fri.iskalnikOseb.dataHelpers;

import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class OdvetnikiData {

	public static void main(String[] args) {
	}

	public static OdvetnikStructure getOdvetnikData(String name)
			throws Exception {
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		webClient.getOptions().setJavaScriptEnabled(false);
		// Get the first page
		final HtmlPage page1 = webClient
				.getPage("http://www.odv-zb.si/imenik/imenik-odvetnikov");
		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		// final HtmlForm form = page1.getFormByName("form");
		final HtmlForm form = (HtmlForm) page1
				.getFirstByXPath("//*[@id=\"main\"]/form[1]");
		final HtmlTextInput name1 = form.getInputByName("s");
		name1.setValueAttribute(name);

		// final HtmlTextInput nameField = form.getInputByName("ime");
		// final HtmlTextInput lastNameField = form.getInputByName("priimek");
		// nameField.setValueAttribute("Janez");
		// lastNameField.setValueAttribute("Ahlin");
		final List<HtmlElement> divs = (List<HtmlElement>) form
				.getByXPath("./input[@id='Search']");
		final HtmlPage page2 = divs.get(0).click();

		// Pridobi naslov pisarne
		final List<HtmlElement> naslov = (List<HtmlElement>) page2
				.getByXPath("//*[@id=\"main\"]/table[1]/tbody/tr/td[3]");
		final List<HtmlElement> kraj = (List<HtmlElement>) page2
				.getByXPath("//*[@id=\"main\"]/table[1]/tbody/tr/td[4]");
		final List<HtmlElement> telefon = (List<HtmlElement>) page2
				.getByXPath("//*[@id=\"main\"]/table[1]/tbody/tr/td[5]");
		String naslov1 = "";
		String kraj1 = "";
		String telefon1 = "";

		if (naslov.size() != 0)
			naslov1 = naslov.get(0).getTextContent();
		if (kraj.size() != 0)
			kraj1 = kraj.get(0).getTextContent();
		if (telefon.size() != 0)
			telefon1 = telefon.get(0).getTextContent();

		if (naslov.size() == 0)
			return null;
		else
			return new OdvetnikStructure(name, naslov1, kraj1, telefon1);
	}

}

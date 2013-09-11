package si.fri.iskalnikOseb.dataHelpers;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ZdravnikiData {
	final static String ginekologi = "/ginekologi.xls";
	final static String osebniZdravniki = "/osebniZdravniki.xls";
	final static String zoboZdravniki = "/zobozdravniki.xls";
	/**
	public static void main(String[] args) {

		try {
			System.out.println("Working Directory = "
					+ System.getProperty("user.dir"));
			FileInputStream ginekologi = new FileInputStream(
					"C:/Users/E520/workspace/IskalnikLjudi/WebContent/WEB-INF/ginekologi.xls");
			FileInputStream zobozdravniki = new FileInputStream(
					("C:/Users/E520/workspace/IskalnikLjudi/WebContent/WEB-INF/zobozdravniki.xls"));
			FileInputStream osebniZdravniki = new FileInputStream(
					("C:/Users/E520/workspace/IskalnikLjudi/WebContent/WEB-INF/osebniZdravniki.xls"));
			String nameOfPerson = "PINTER BOJANA";
			ZdravnikStructure zdravnik = getZdravnikData(new FileInputStream[] {
					ginekologi, zobozdravniki, osebniZdravniki }, nameOfPerson);

			if (zdravnik == null)
				System.out.println("Zdravnik ne obstaja");
			else {
				System.out.println("Ime: " + zdravnik.getIme());
				System.out.println("Izpostava: " + zdravnik.getNazivOE());
				System.out
						.println("Izvajalec: " + zdravnik.getNazivIzvajalca());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
*/
	
	public static ZdravnikStructure getZdravnikData(String path,
			String name) {
		try {
			ZdravnikStructure ginekolog = aliJeZdravnikPosamezno1(name,
					path.concat(ginekologi));
			if (ginekolog != null)
				return ginekolog;

			ZdravnikStructure zdravnik = aliJeZdravnikPosamezno1(name, path.concat(osebniZdravniki));
			if (zdravnik != null)
				return zdravnik;

			ZdravnikStructure zoboZdravnik = aliJeZdravnikPosamezno1(name,path.concat(zoboZdravniki)
					);
			if (zoboZdravnik != null)
				return zoboZdravnik;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static ZdravnikStructure aliJeZdravnikPosamezno1(String name,
			String path) throws Exception {
		String[] nameSplit = name.split(" ");
		String name1 = nameSplit[1].concat(" ").concat(nameSplit[0]);
		// Dobimo workbook instanco na�e datoteke
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));

		// Dobimo prvo stran datoteke
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Iterator po vrsticah na trenutni strani
		Iterator<Row> rowIterator = sheet.iterator();

		int i = 0;
		while (rowIterator.hasNext()) {
			i++;
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			if (i >= 8) {
				String nazivOE = "";
				String nazivIzvajalca = "";
				int j = 0;
				while (cellIterator.hasNext()) {
					j++;
					Cell cell = cellIterator.next();
					if (j == 2)
						nazivOE = cell.getStringCellValue();
					else if (j == 4)
						nazivIzvajalca = cell.getStringCellValue();
					else if (j == 6
							&& name1.toUpperCase().equals(
									cell.getStringCellValue()))
						return new ZdravnikStructure(name, nazivOE,
								nazivIzvajalca);
				}
			}
		}
		return null;
	}
}

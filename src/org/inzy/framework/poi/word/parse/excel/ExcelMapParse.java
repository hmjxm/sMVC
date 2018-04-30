package org.inzy.framework.poi.word.parse.excel;

import java.util.Iterator;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.inzy.framework.poi.word.util.ParseWordUtil;

public final class ExcelMapParse {
	public static void parseNextRowAndAddRow(XWPFTable table, int index,
			List<Object> list) throws Exception {
		XWPFTableRow currentRow = table.getRow(index);
		String[] params = parseCurrentRowGetParams(currentRow);
		table.removeRow(index);
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			Object obj = localIterator.next();
			currentRow = table.createRow();
			for (int cellIndex = 0; cellIndex < currentRow.getTableCells()
					.size() || cellIndex < params.length; cellIndex++) {
				if (cellIndex < currentRow.getTableCells().size()) {
					((XWPFTableCell) currentRow.getTableCells().get(cellIndex))
							.setText(ParseWordUtil.getValueDoWhile(obj,
									params[cellIndex].split("\\."), 0)
									.toString());

				} else {
					currentRow.createCell().setText(
							ParseWordUtil.getValueDoWhile(obj,
									params[cellIndex].split("\\."), 0)
									.toString());
				}

			}
		}
	}

	private static String[] parseCurrentRowGetParams(XWPFTableRow currentRow) {
		List<XWPFTableCell> cells = currentRow.getTableCells();
		String[] params = new String[cells.size()];

		for (int i = 0; i < cells.size(); i++) {
			String text = ((XWPFTableCell) cells.get(i)).getText();
			params[i] = (text == null ? "" : text.trim().replace("{{", "")
					.replace("}}", ""));
		}
		return params;
	}
}
package pageElements;

import base.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Table extends ElementBase {

    public Table(SearchContext host, By locator) {
        super(host, locator);
    }

    public List<WebElement> getHeader() {
        return element.findElements(By.tagName("th"));
    }

    public List<String> getHeaderAsString() {
        List<String> headersAsString = new ArrayList<String>();
        for (WebElement header : getHeader()) {
            headersAsString.add(header.getText());
        }
        return headersAsString;
    }

    public List<List<WebElement>> getRows() {
        List<List<WebElement>> rows = new ArrayList<List<WebElement>>();
        for (WebElement row : element.findElements(By.cssSelector("tbody tr"))) {
            rows.add(row.findElements(By.tagName("td")));
        }
        return rows;
    }

    public List<List<String>> getRowsAsString() {
        List<List<String>> rowsAsString = new ArrayList<List<String>>();
        for (List<WebElement> row : getRows()) {
            List<String> rowAsString = new ArrayList<String>(row.size());
            for (WebElement col : row) {
                rowAsString.add(col.getText());
            }
            rowsAsString.add(rowAsString);
        }
        return rowsAsString;
    }

    public List<List<WebElement>> getColumns() {
        List<List<WebElement>> rows = getRows();
        List<List<WebElement>> columns = new ArrayList<List<WebElement>>(rows.size());
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).size(); j++) {
                if (columns.size() - 1 < j) {
                    columns.add(j, new ArrayList<WebElement>());
                }
                columns.get(j).add(rows.get(i).get(j));
            }
        }
        return columns;
    }

    public List<List<String>> getColumnsAsString() {
        List<List<String>> colsAsString = new ArrayList<List<String>>();
        for (List<WebElement> row : getColumns()) {
            List<String> colAsString = new ArrayList<String>(row.size());
            for (WebElement col : row) {
                colAsString.add(col.getText());
            }
            colsAsString.add(colAsString);
        }
        return colsAsString;
    }

    public List<WebElement> getColumn(int col) {
        return getColumns().get(col);
    }

    public List<WebElement> getRow(int row) {
        return getRows().get(row);
    }

    public WebElement getCell(int row, int col) {
        return getRow(row).get(col);
    }
}

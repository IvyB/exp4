import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class FileCatalogLoader implements CatalogLoader{


	@Override
	public Catalog loadCatalog(String fileName) throws FileNotFoundException,
			IOException, DataFormatException {

		Catalog catalog = new Catalog();
		BufferedReader reader=
				new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();

		//逐行读取产品信息
		while (line!=null) {

			if (line.startsWith("Brewer")) {
				catalog.addProduct(readCoffeeBrewer(line));
			}
			else if (line.startsWith("Coffee")) {
				catalog.addProduct(readCoffee(line));
			}
			else if (line.startsWith("Product")) {
				catalog.addProduct(readProduct(line));
			}
			else {
				throw new DataFormatException("Error Prefix");
			}
			line = reader.readLine();
		}
		reader.close();
		//返回读取到的所有产品信息
		return catalog;
	}

	private Product readProduct(String line)throws DataFormatException{
		//使用StringTokenizer类解析Product信息
		StringTokenizer tokenizer=new StringTokenizer(line,"_");
		//如果Product信息格式不正确或不完整则抛出异常
		if(tokenizer.countTokens()!=4){
			throw new DataFormatException("产品信息错误");
		}
		else {
			String string=tokenizer.nextToken();
			Product tempProduct=
					new Product(tokenizer.nextToken(),
							tokenizer.nextToken(),Double.parseDouble(tokenizer.nextToken()));
			return tempProduct;
		}
	}

	private Coffee  readCoffee (String line)throws DataFormatException{
		//使用StringTokenizer类解析Coffee信息
		StringTokenizer tokenizer=new StringTokenizer(line,"_");
		//如果Coffee信息格式不正确或不完整则抛出异常
		if(tokenizer.countTokens()!=10)
			throw new DataFormatException("产品信息错误");
		else {
			String string=tokenizer.nextToken();
			Coffee tempCoffee=
					new Coffee(tokenizer.nextToken(),
							tokenizer.nextToken(),Double.parseDouble(tokenizer.nextToken()),
							tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
							tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
			return tempCoffee;
		}
	}

	private CoffeeBrewer  readCoffeeBrewer (String line)throws DataFormatException{
		//使用StringTokenizer类解析CoffeeBrewer信息
		StringTokenizer tokenizer=new StringTokenizer(line,"_");
		//如果CoffeeBrewer信息格式不正确或不完整则抛出异常
		if(tokenizer.countTokens()!=7)
			throw new DataFormatException("产品信息错误");
		else {
			String string=tokenizer.nextToken();
			CoffeeBrewer tempCoffeeBrewer=
					new CoffeeBrewer(tokenizer.nextToken(),
							tokenizer.nextToken(),Double.parseDouble(tokenizer.nextToken()),
							tokenizer.nextToken(), tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()));
			return tempCoffeeBrewer;

		}

	}



}
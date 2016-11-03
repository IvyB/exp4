
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
		
		//
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

		return catalog;
	}
	
	private Product readProduct(String line)throws DataFormatException{
		//
		StringTokenizer tokenizer=new StringTokenizer(line,"_");
		//
		if(tokenizer.countTokens()!=4){
			throw new DataFormatException("��Ʒ��Ϣ����");
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
		//
		StringTokenizer tokenizer=new StringTokenizer(line,"_");
		//
		if(tokenizer.countTokens()!=10)
			throw new DataFormatException("��Ʒ��Ϣ����");
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
		//
		StringTokenizer tokenizer=new StringTokenizer(line,"_");
		//
		if(tokenizer.countTokens()!=7)
			throw new DataFormatException("��Ʒ��Ϣ����");
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

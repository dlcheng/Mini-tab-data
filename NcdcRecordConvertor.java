import java.io.*;

public class NcdcRecordConvertor {
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			System.err.println("Usage: command <input> <output>");
			System.exit(-1);
		}

		String inputFile = args[0];
		String outputFile = args[1];
		NcdcRecordParser parser = new NcdcRecordParser();

		BufferedReader reader = null;
		BufferedWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(inputFile));
			writer = new BufferedWriter(new FileWriter(outputFile));
			String oneLine = null;
			while ((oneLine = reader.readLine()) != null)	{
				parser.parse(oneLine);
				String context = parser.getYear() + "\t" + parser.getAirTemperatureString()
								+ "\t" + parser.getQuality() + "\n";
				writer.write(context);
			}
		} finally {
			reader.close();
			writer.close();
		}
	}
}
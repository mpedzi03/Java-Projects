import java.io.*;
import java.util.Random;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class JSONCircles {
	public static void main(String[] args){
		// randomly come up with data related to circles
		// create json objects of these
		// store them in a json list
		// write that data out to a text file 
		Random rnd = new Random();
		int howMany = 1 + rnd.nextInt(20);
		int x, y, r;
		JSONArray list = new JSONArray();
		JSONObject circle;
		for (int i = 0; i < howMany; i++){
			x = rnd.nextInt(400);
			y = rnd.nextInt(400);
			r = rnd.nextInt(50);
			circle = new JSONObject();
			circle.put("x", x);
			circle.put("y", y);
			circle.put("radius", r);
			System.out.printf("%d %d %d", x, y, r);
			list.add(circle);
		}
		JSONObject data = new JSONObject();
		data.put("circles",  list);
		
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					new File("circles.json"))));
			pw.println(data.toJSONString());
			pw.close();
		}catch (Exception ex){
			System.out.println("Bad stuff happened.");
			ex.printStackTrace();
		}
	}

}

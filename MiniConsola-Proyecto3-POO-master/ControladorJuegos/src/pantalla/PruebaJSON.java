package pantalla;

import java.awt.Color;

import javax.swing.JButton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PruebaJSON {

	public static int matriz[][] = new int[50][50];

	public static void llenarMatriz() {
		for (int i=0; i<50; i++) {
			for (int j=0; j<50; j++) {
				if (i==24 && j==24) {
				
					matriz[i][j] =  1;
					
				}
				else {
					matriz[i][j] =  0;
				}
			}
		}
	}
	
	public static JSONArray matriz_Json () {
		JSONArray jsonarray = new JSONArray();
		for (int i=0; i<50; i++) {
			JSONArray jsonFila = new JSONArray();
			for (int j=0; j<50; j++) {
				JSONObject jsonValor = new JSONObject();
				jsonValor.put("valor", matriz[i][j]);
				jsonFila.add(jsonValor);
			}
			jsonarray.add(jsonFila);
		}
		System.out.println(jsonarray);
		return jsonarray;
	}
	
	public static int[][] retornaMatriz (JSONArray json){
		int newMatriz[][] = new int[50][50];
		for (int i=0; i<50; i++) {
			JSONArray array = (JSONArray) json.get(i);
			for (int j=0; j<50; j++) {
				JSONObject valor = (JSONObject) array.get(j);
				int numero = (Integer) valor.get("valor");
				newMatriz[i][j] = numero;
			}
		}
		return newMatriz;
	}
	
	public static void imprimeMatriz ( int[][]  matriz) {
		System.out.println();
		for (int i=0;i<50;i++) {
			for (int j=0;j<50;j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println();
	}}
	
	
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("algo", 1);
		
		matriz_Json ();
		imprimeMatriz(retornaMatriz(matriz_Json ()));

	}

}

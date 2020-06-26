/**
 * 
 */
package ayers.jetbrains;

import java.util.Scanner; 

/**
 * @author cggra
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int oldbase = scanner.nextInt();
		String temp = scanner.next();
        int newbase = scanner.nextInt();
		boolean isFraction = true;
		double decimalValue = 0.0;
        StringBuilder result = new StringBuilder();
		
        scanner.close();
        
		if (temp.indexOf(".") < 0) {
			isFraction = false;
		}
		
		decimalValue = convertToDecimal(temp, oldbase);
		result = convertFromDecimal(decimalValue, newbase);
        
        if (!isFraction) {	//truncate decimal part
			if (result.indexOf(".") >= 0) {
				result.delete(result.indexOf("."), result.length());
			}
		}
        System.out.println(result);
    }
	
	// Convert from Decimal using Long.toString(sourceNumber, destinationRadix)
	static StringBuilder convertFromDecimal(double value, int base) {				
		StringBuilder result = new StringBuilder();
		
		if (base == 1) {
			for (int i = 0 ; i < Math.floor(value); i++) {
				result.append("1");
			}
		} else if (base == 10) {
			result.append(Double.toString(value));
		} else {
			String[] temp;
			
			temp = Double.toString(value).split("\\.");
			result.append(Long.toString(Long.parseLong(temp[0]), base));
			
			if (!temp[1].isEmpty()) {
				result.append(convertFractionFromDecimal(temp[1], base));
			}
        }
		
		return result;
	}
	
	static String convertFractionFromDecimal(String value, int base) {
		String temp = "";
		char ch;
		double dValue = Double.parseDouble(("." + value));
		
		for (int i =0; i < 5; i++) {
			dValue = dValue * base;
			if ((int)Math.floor(dValue) < 10 || base < 10) {
				temp = temp + Integer.toString((int)Math.floor(dValue));
			} else {	// letters
				ch = (char)((int) Math.floor(dValue) - 10 + 'a');
				temp = temp + ch;
			}
			dValue -= Math.floor(dValue);
		}
		temp = "." + temp;
		return temp;
	}
	
	// Convert to Decimal using Integer.parseInt(number, sourceBase)
	static double convertToDecimal(String value, int base) {
        double decimalValue = 0.0;
		String[] temp = new String[2];
		
		if (value.indexOf(".") >= 0 ) {
			temp = value.split("\\.");
		} else {
			temp[0] = value;
			temp[1] = "";
		}
		
		if (base == 1) {
            decimalValue = temp[0].length();
        } else if (base == 10) {
			decimalValue = Double.parseDouble(value);
		} else if (base < 10){
			decimalValue = Integer.parseInt(temp[0], base);
            
			if (!temp[1].isEmpty()) {
                decimalValue += convertFractionToDecimal(temp[1], base);
            }
        } else {	// letters
			decimalValue = Integer.parseInt(temp[0], base);
			
			if (!temp[1].isEmpty()) {
                decimalValue += convertFractionToDecimal(temp[1], base);
            }
		}
		
		return decimalValue;
	}
    
	// x/(base^1) + y/(base^2) + z/(base^3)...
	static double convertFractionToDecimal(String value, int base) {
        double result = 0.0;
		double val = 0.0;
        int ch = 0;
		
		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) >= 'a' && value.charAt(i) <= 'z') {
                ch = value.charAt(i) - 'a' + 10;
				val = (double) ch / (double) Math.pow(base,i+1); 
				result += val;
            } else {
				val = (double) Integer.parseInt(String.valueOf(value.charAt(i))) / (double) Math.pow(base,i+1);
				result += val;
            }
        }
        return result;
    }
	
}
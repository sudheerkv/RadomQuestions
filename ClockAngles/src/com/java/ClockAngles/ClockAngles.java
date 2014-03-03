package com.java.ClockAngles;

import java.io.*;
import java.text.DecimalFormat;

public class ClockAngles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String curDir = System.getProperty("user.dir");
		BufferedReader in = null;
		BufferedWriter out = null;

		String currentLine = null;
		final File textFile = new File(curDir + "/SampleInput.txt");
		final File outputFile = new File(curDir + "/Output.txt");
		int firstline = 0;
		int NValue = 0;
		int HrHand = 0, MinHand = 0, SecHand = 0;

		try {
			in = new BufferedReader(new FileReader(textFile));
			out = new BufferedWriter(new FileWriter(outputFile));
			while ((currentLine = in.readLine()) != null) {
				firstline++;
				if (firstline == 1) {
					NValue = Integer.parseInt(currentLine);
					out.write(NValue + "\n");
				} else {
					HrHand = Integer.parseInt(currentLine.substring(0, 2));
					MinHand = Integer.parseInt(currentLine.substring(3, 5));
					SecHand = Integer.parseInt(currentLine.substring("hr:mm:"
							.length()));
					String HrMinAngle = CalculateHrMinuteAngle((double) HrHand,
							(double) MinHand, (double) SecHand);
					String HrSecAngle = CalculateHrSecAngle((double) HrHand,
							(double) MinHand, (double) SecHand);
					String MinSecAngle = CalculateMinSecAngle((double) MinHand,
							(double) SecHand);
					out.write(HrMinAngle + ", " + HrSecAngle + ", " + MinSecAngle
							+ "\n");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static String CalculateHrMinuteAngle(double Hr, double Min, double Sec) {
		double HrMinAngle = 0.0;
		DecimalFormat f = new DecimalFormat("0.00");
		/*
		 * 1 hr - 30 degrees 60 min - 30 degrees => 1 min - 1/2 degrees 3600 sec
		 * - 30 degrees => 1 sec - 1/120 degrees 1 min - 360/60 => 6 degrees 60
		 * sec - 6 degrees => 1 sec - 1/10 degress
		 */
		HrMinAngle = (30.0 * Hr + Min / 2.0 + Sec / 120.0)
				- (6 * Min + Sec / 10.0);
		if (HrMinAngle < 0)
			HrMinAngle *= -1;
		else if (HrMinAngle > 180)
			HrMinAngle = 360 - HrMinAngle;
		return f.format((double) Math.round(HrMinAngle * 100) / 100);
	}

	static String CalculateHrSecAngle(double Hr, double Min, double Sec) {
		double HrSecAngle = 0.0;
		DecimalFormat f = new DecimalFormat("0.00");
		HrSecAngle = (30.0 * Hr + Min / 2.0 + Sec / 120.0) - (6 * Sec);
		if (HrSecAngle < 0)
			HrSecAngle *= -1;
		else if (HrSecAngle > 180)
			HrSecAngle = 360 - HrSecAngle;
		return f.format((double) Math.round(HrSecAngle * 100) / 100);
	}

	static String CalculateMinSecAngle(double Min, double Sec) {
		double MinSecAngle = 0.00;
		DecimalFormat f = new DecimalFormat("0.00");
		MinSecAngle = (double) ((6.0 * Min + Sec / 10.0) - (6 * Sec));
		if (MinSecAngle < 0)
			MinSecAngle *= -1;
		else if (MinSecAngle > 180)
			MinSecAngle = 360 - MinSecAngle;
		return f.format((double) Math.round(MinSecAngle * 100) / 100);
	}
}

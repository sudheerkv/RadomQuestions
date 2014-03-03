package com.java.FollowDirections;

import java.io.*;

public class FollowDirections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String curDir = System.getProperty("user.dir");
		BufferedReader in = null;
		BufferedWriter out =null;
		
		int icap =0;
		int jcap =0;
		int jdirection =1;
		int idirection =0;
		
		String currentLine;
		final File textFile = new File(curDir + "/SampleInput.txt");
		final File outputFile = new File(curDir + "/Output.txt");
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			in = new BufferedReader(new FileReader(textFile));
			while ((currentLine = in.readLine()) != null) {
					if(currentLine.contains("Move")) {
						if(idirection ==1 || idirection ==-1) {
							icap=icap+idirection * Integer.parseInt(currentLine.substring("Move".length()+1));
						}
						else if(jdirection ==1 || jdirection ==-1) {
							jcap=jcap+jdirection*Integer.parseInt(currentLine.substring("Move".length()+1));
						}
					}
					else if(currentLine.contains("Turn")) {
						if(currentLine.substring("Turn".length()+1).equals("right") && (jdirection==1 || jdirection==-1)) {
							idirection =1*jdirection;
							jdirection =0;
						}
						else if(currentLine.substring("Turn".length()+1).equals("left") && (jdirection==1 || jdirection==-1)){
							idirection =-1*jdirection;
							jdirection =0;
						}
						else if(currentLine.substring("Turn".length()+1).equals("right") && (idirection==1 || idirection==-1)) {
							jdirection =-1*idirection;
							idirection =0;
						}
						else if(currentLine.substring("Turn".length()+1).equals("left") && (idirection==1 || idirection==-1)){
							jdirection =1*idirection;
							idirection =0;
						}
					}
					stringBuilder.append(currentLine);
					stringBuilder.append(ls);
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			out = new BufferedWriter(new FileWriter(outputFile));
			out.write(icap+","+jcap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

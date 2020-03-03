//-----------------------------------------//
// MATH226 - Numerical Methods for EE
// Project 01
//
// Name-Surname: <Alper Salik>
// Student ID: <041502005>
//-----------------------------------------// 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.*;
public class LU {
	
	public static void main(String[] args) throws IOException{
		File file=new File("matris.txt");

		Scanner scan=new Scanner(file);

		int i=0;
		int j,k;
		int n=0;
		String z;
		//this part finds how many rows or column

		while(scan.hasNextLine()){
			z =scan.nextLine();
			n++;
		}
		double d[][]= new double[n][n];
		double a[][]= new double[n][n];

		//this part read matrix from file and put it in a and d matrix we use a for A=LU and use d for partial pivoting

		Scanner input=new Scanner(file);
		while(input.hasNextLine()){
			String string[]=input.nextLine().split(" ");
			for(j=0;j<n;j++){
				a[i][j]=Double.parseDouble(string[j]);
				d[i][j]=Double.parseDouble(string[j]);
			}	i++;
		}

		// this part print a matrix to the screen

		System.out.print("A:");
		for ( i = 1; i < n+1; i++) {
			System.out.println("");
			for (j = 1; j < n+1; j++) {
				System.out.print(a[i-1][j-1] + " ");
			}}
		double q=0;		

		//this part find L and U and put them to a matrix

		for( i=1;i<=n;i++){
			for(j=1;j<=i-1;j++){
				q=a[i-1][j-1];
				for( k=1;k<=j-1;k++){
					q=q-a[i-1][k-1]*a[k-1][j-1];
				}
				a[i-1][j-1]=q/(a[j-1][j-1]);
			}

			for(j=i;j<=n;j++){ 
				q=a[i-1][j-1];
				for( k=1;k<=i-1;k++){
					q=q-a[i-1][k-1]*a[k-1][j-1];
				}
				a[i-1][j-1]=q;
			}
		}
		System.out.println("");

		// this part describes L matrix and print it

		double l[][]= new double[n][n];
		System.out.print("L:");
		for ( i = 1; i < n+1; i++) {
			System.out.println("");
			for (j = 1; j <n+1; j++) {
				if(i>j){
					l[i-1][j-1]=a[i-1][j-1];
					System.out.print(l[i-1][j-1] + " ");}
				else if(i==j){
					l[i-1][j-1]=1;
					System.out.print(l[i-1][j-1]+ " ");}
				else{
					l[i-1][j-1]=0;
					System.out.print(l[i-1][j-1] + " ");
				}
			}}
		System.out.println("");

		//this part describes U matrix and print it

		System.out.print("U:");
		double u[][]= new double[n][n];
		for ( i = 1; i < n+1; i++) {
			System.out.println("");
			for (j = 1; j < n+1; j++) {
				if(i<=j){
					u[i-1][j-1]=a[i-1][j-1];
					System.out.print(u[i-1][j-1] + " ");}
				else{
					u[i-1][j-1]=0;
					System.out.print(u[i-1][j-1]+ " ");}
			}}
		System.out.println();

		//this part does singularity check 

		int c=0;
		double p=0.000000001;
		System.out.println("");
		for(i=0;i<n;i++){
			if(a[i][i]<p)
				c++;}
		if(c>0)				
			System.out.println("Matris A is singular");
		else
			System.out.println("Matris A is nonsingular");

		System.out.println();
		System.out.println("LUx=b");
		System.out.println();

		//this part read b matrix from file

		double b[]= new double[n];
		File file1=new File("matris_b.txt");
		Scanner scan1=new Scanner(file1);
		while(scan1.hasNext()){
			for(i=0;i<n;i++){
				b[i]=scan1.nextDouble();
			}
		}

		for(i=0;i<n;i++)
			System.out.println("b"+i+":"+" "+b[i]);

		System.out.println();

		double x1[]= new double[n];
		double  y[]= new double[n];	

		//this part does Ly=b and find y

		for(int x=0; x<n; x++) {
			y[x]= b[x];
			for(int z1=0; z1<=x-1; z1++) {
				y[x] = y[x]-(y[z1]*l[x][z1]);
			}
		}

		//this part does Ux=y and find x 
		double hold1,hold2,hold3;
		for(int x=n-1; x>=0; x--) {
			hold3= y[x];
			for(int z1=x+1; z1<n; z1++) {
				hold1 = x1[z1];
				hold2 = u[x][z1];
				hold3 = hold3 - hold1*hold2;
				
			}
			x1[x] = hold3/u[x][x];
		}
        //this part prints x to the screen
		for(i=0;i<n;i++) 
			System.out.println("x"+i+":"+" "+x1[i]);

		//After this part I try to do partial pivoting but ý didn't finish it
		
		
		System.out.println();
		//	Create idendity matrix
		double idendity[][] = new double[d.length][d[0].length];



		double m[][] = new double[d.length][d[0].length];

		for(i=0;i<idendity.length;i++){
			for(j=0;j<idendity[i].length;j++){
				if(i==j){
					idendity[i][j] = 1; 
					m[i][j] = 1;
				}
			}
		}
		
		double copy[][] = new double[d.length][d.length];

		for(i=0;i<idendity.length;i++){
			for(j=0;j<idendity[i].length;j++){
				copy[i][j]=d[i][j];
			}
		}

		for(j=0;j<d.length;j++){

			double mt[][] = idendity;
			double pivot;
			double max = copy[j][j];
			int index = j;

			for(i = j;i<d.length;i++){
				if(Math.abs(copy[i][j]) > Math.abs(copy[index][j])){
					max = copy[i][j];
					index = i;
				}
			}
			if(max != 0){
				double[] hold = new double[3];
				hold = copy[j];
				copy[j] = copy[index];
				copy[index] = hold;
			}
			
			for(k = j+1; k< copy.length;k++){
				double holdk = copy[k][j];
				double holdt = idendity[k][k];
				double holdz = copy[j][j];
				mt[j][k] = (-1) * ( holdk * holdt ) / holdz;
			}

			for(int count1=0;count1<copy.length;count1++){
				for(int count2=0;count2<copy.length;count2++){
					for(int count3=0;count3<copy.length;count3++){
						copy[count1][count2]=copy[count1][count2]+mt[count1][count3]*copy[count3][count2];
					}
				}
			}

			for(int count1=0;count1<copy.length;count1++){
				for(int count2=0;count2<copy.length;count2++){
					for(int count3=0;count3<copy.length;count3++){
						m[count1][count2]=m[count1][count2]+mt[count1][count3]*m[count3][count2];
					}
				}
			}
			
			
		}
		
	}

}

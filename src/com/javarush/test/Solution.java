package com.javarush.test;

import Jama.Matrix;

import java.util.Scanner;

public class Solution {
    double[] z = new double[3];
    Scanner scn=new Scanner (System.in);

    public double[][] retX() {
        //double [] [] mas=new double [scn.nextInt()] [scn.nextInt()];
        double [] [] mas=new double [4] [5];
        for (int i=0; i<mas.length; i++){
            System.out.println("Введите "+(i+1)+" строку");
            for (int j=0; j<mas[0].length; j++){
                mas [i] [j]= scn.nextDouble();
            }
        }
        return mas;
    }

    public  double[] retY(int size){
        double[] mas=new double[size];

        for(int i=0; i<mas.length; i++){
            mas[i]=scn.nextDouble();
        }
        return mas;
    }
    public static void main(String[] args) {
        Solution H = new Solution();
        double[][] x = H.retX();
        double[] y = H.retY(x[0].length);
        double[][] f = new double[3][5];
        for (int i = 0; i < 5; i++) {
            f[0][i] = 1;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                System.out.println("i=" + i);
                System.out.println("j=" + j);
                for (int k = 0; k < 5; k++) {
                    if (k == 1) {
                        f[1][k] = 0;
                        f[2][k] = 0;
                    }
                    if (k==2){
                        f[1][k] = 2/x[i][k];
                        f[2][k] = 2/x[j][k];
                    }
                    if(k==3){
                        f[1][k] = 1/x[i][k];
                        f[2][k] = 1/x[j][k];
                    }
                    if(k==4){
                        f[1][k] = 0;
                        f[2][k] = 0;
                    }
                    System.out.println("f[1][" + k + "]=" + f[1][k]);
                    System.out.println("f[2][" + k + "]=" + f[2][k]);
                }
                H.test(f, y);
            }
        }
    }

    public void test(double[][] ff, double[] yy) {
        Matrix A1 = new Matrix(ff);
        A1.print(10, 2);
        Matrix B1 = A1.transpose();
        Matrix F1 = A1.times(B1);
        Matrix F4 = F1.inverse();
        Matrix F2 = F4.times(A1);
        Matrix C = new Matrix(yy, 5);
        Matrix F3 = F2.times(C);
        z = F3.getColumnPackedCopy();
        for (int i = 0; i < 3; i++)
            System.out.println("z[" + i + "]=" + z[i]);

    }
}


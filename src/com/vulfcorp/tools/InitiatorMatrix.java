package com.vulfcorp.tools;

import com.vulfcorp.interfaces.IMatrix;

public class InitiatorMatrix {
    public static IMatrix FillMatrix(IMatrix matrix, int countNotNullNumbers, int maxNumber)
    {
        int columns = matrix.getColumnCount();
        int lines = matrix.getLineCount();
        int notNullNumbers = countNotNullNumbers;

        if(countNotNullNumbers > columns*lines)
            throw new IllegalArgumentException("Matrix's capacity = "+(columns*lines)+" | countNotNullNumbers = "+countNotNullNumbers);
        /*
            Follow the strategy
        */
        notNullNumbers = FillLikeSpareMatrix(matrix, notNullNumbers, maxNumber);
        if(notNullNumbers!= 0)
            FillLikeNormalMatrix(matrix, notNullNumbers, maxNumber);

        return matrix;
    }


    private static int FillLikeSpareMatrix(IMatrix matrix, int notNullNumbers, int maxNumber)
    {
        int columns = matrix.getColumnCount();
        int lines = matrix.getLineCount();

        for(int i = 0; i < lines && notNullNumbers!=0; i++){
            for(int j = 1; j < columns && notNullNumbers!=0; j+=2){
                matrix.writeRecord(i,j,getRandomNumber(maxNumber));
                notNullNumbers--;
            }
        }
        return notNullNumbers;
    }

    private static void FillLikeNormalMatrix(IMatrix matrix, Integer notNullNumbers, int maxNumber)
    {
        int columns = matrix.getColumnCount();
        int lines = matrix.getLineCount();

        for(int i = 0; i < lines && notNullNumbers!=0; i++){
            for(int j = 0; j < columns && notNullNumbers!=0; j+=2){
                matrix.writeRecord(i,j,getRandomNumber(maxNumber));
                notNullNumbers--;
            }
        }
    }

    private static int getRandomNumber(int maxNumber){
       int result = 0;
       do{
           result = (int)(Math.random()*(maxNumber + 1));
       }while(result == 0);
       return result;
    }
}

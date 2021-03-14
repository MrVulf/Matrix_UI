package com.vulfcorp.tools;

import com.vulfcorp.interfaces.IMatrix;

public class StatisticMatrix {
    private IMatrix matrix;
    private int columns;
    private int lines;

    public StatisticMatrix(IMatrix matrix) {
        this.matrix = matrix;
        columns = matrix.getColumnCount();
        lines = matrix.getLineCount();
    }

    public int sumMatrixNumbers(){
        int sum = 0;
        for(int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                sum+=matrix.readRecord(i,j);
            }
        }
        return sum;
    }

    public double avgNumber(){
        return sumMatrixNumbers()/((double)lines*columns);
    }

    public int maxNumberInMatrix(){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                int record = matrix.readRecord(i,j);
                if(record>max)
                    max=record;
            }
        }
        return max;
    }

    public int countNotNullNumbers(){
        int count = 0;
        for(int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix.readRecord(i,j) != 0)
                    count++;
            }
        }
        return count;
    }
}

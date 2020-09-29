import java.util.*;

public class CellIterator implements Iterator<Cell> {
    Cell[][] dataArray;
    List<Cell> dataList;
    int outer;
    int inner;
    int index;
    boolean runOut;
    CellStates cellState;

    public CellIterator(Cell[][] data){
        this.dataArray = data;
        outer = 0;
        inner = 0;
        runOut = false;
        cellState = CellStates.Array;
    }

    public CellIterator(List<Cell> data){
        this.dataList = data;
        index = 0;
        if (dataList.isEmpty())
        {
            runOut = true;
        }
        else
        {
            runOut = false;
        }
        cellState = CellStates.List;
    }

    @Override
    public boolean hasNext() {
        return !runOut;
    }

    @Override
    public Cell next() {
        Cell ret = null;

        switch (cellState)
        {
            case Array:
                ret = dataArray[outer][inner];
                inner++;

                if (inner >= dataArray[outer].length){
                    inner = 0;
                    outer++;

                    if (outer >= dataArray.length){
                        runOut = true;
                    }
                }

                break;

            case List:
                ret = dataList.get(index);
                index++;

                if (index >= dataList.size())
                {
                    runOut = true;
                }

                break;
        }
        return ret;
    }

    public enum CellStates
    {
        Array,
        List 
    }
}